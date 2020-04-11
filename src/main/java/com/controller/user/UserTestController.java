package com.controller.user;

import com.model.*;
import com.model.enums.ProcedureStatus;
import com.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
public class UserTestController {

    private static final Logger logger = LogManager.getLogger(UserTestController.class);

    @Autowired
    ConversationService conversationService;

    @Autowired
    AntiBullyingReportService antiBullyingReportService;

    @Autowired
    UserService userService;

    @Autowired
    ProcedureService procedureService;

    @Autowired
    MessageService messageService;

    @Autowired
    ConversationUserService conversationUserService;

    /**
     * Processes the petition to get to the conversation page.
     *
     * @return ModelAndView with the desired .jsp file and its required model & objects
     */
    @RequestMapping(value = "/user/messages")
    public ModelAndView messages(HttpServletRequest request) {
        Message message = new Message();

        try {

            User user = (User) request.getSession().getAttribute("user");
            List<Conversation> conversations;
            if (user == null) {
                conversations = conversationService.findAllConversationsByUserId("1");
                user = userService.getUserById("1");
            } else
                conversations = conversationService.findAllConversationsByUserId(user.getUserId());

            logger.info("[" + new Object() {
            }.getClass().getEnclosingMethod().getName() + "] - Conversations loaded successfully");

            Collections.sort(conversations);

            for (Conversation conversation : conversations) {
                conversation.setUnread(conversationUserService.findUnread(user.getUserId(), conversation.getConversationId()));
            }

            logger.info("[" + new Object() {
            }.getClass().getEnclosingMethod().getName() + "] - Unread conversations successfully found");


            ModelAndView model = new ModelAndView("/message");
            model.addObject("conversations", conversations);
            model.addObject("message", message);
            return model;
        } catch (Exception e) {
            logger.error("[" + new Object() {
            }.getClass().getEnclosingMethod().getName() + "] -  Error accessing the messaging page: " + e);

            return null;
        }
    }


    /**
     * Processes the creation of a new ticket by using all parameters from the "New Ticket" form.
     *
     * @param message the message with all its elements
     * @return returns the user to the main page with an url
     */
    @RequestMapping(value = "/message/creation", method = RequestMethod.POST)
    public String createMessage(@Valid @ModelAttribute(value = "message") Message message, BindingResult result, HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");

        logger.info("[" + new Object() {
        }.getClass().getEnclosingMethod().getName() + "] -  Session user successfully loaded");

        try {
            if (user == null)
                message.setSender(userService.getUserById("1"));
            else
                message.setSender(user);

            message.setDate(new Date());

            messageService.addMessage(message);

            Conversation conversation = conversationService.getWithUsers(message.getConversation().getConversationId());
            conversation.setLastMessageDate(new Date());
            conversationService.addConversation(conversation);

            List<ConversationUser> conversationUsers = conversation.getUserConversations();

            for (ConversationUser cu : conversationUsers) {
                if (!cu.getUser().equals(user)) {
                    cu.newMessage();
                    conversationUserService.addConversationUser(cu);
                }
            }

            String referer = request.getHeader("Referer");

            return "redirect:" + referer;
        } catch (Exception e) {
            logger.error("[" + new Object() {
            }.getClass().getEnclosingMethod().getName() + "] -  Error creating the message: " + e);

            return null;
        }

    }

    /**
     * Processes the petition to get to the anti-bullying report creation page.
     *
     * @return ModelAndView with the desired .jsp file and its required model & objects
     */
    @RequestMapping(value = "/user/antibullying")
    public ModelAndView antiBullyingAccess(@RequestParam Boolean observed) {
        //FALTA AGAFAR L'USUARI!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        try {

            logger.info("[" + new Object() {
            }.getClass().getEnclosingMethod().getName() + "] -  Session user successfully loaded");

            AntiBullyingReport newReport = new AntiBullyingReport();
            newReport.setObserved(observed);
            ModelAndView model = new ModelAndView("/createReport");
            model.addObject("report", newReport);

            return model;
        } catch (Exception e) {
            logger.error("[" + new Object() {
            }.getClass().getEnclosingMethod().getName() + "] -  Error accessing the anti-bullying report page: " + e);

            return null;
        }
    }

    /**
     * Processes the creation of a new anti-bullying report by using all parameters from the "Anti-Bullying Report" form.
     *
     * @param report the anti-bullying report with all its elements
     * @return returns the user to the main page with an url
     */
    @RequestMapping(value = "/user/antibullying/report", method = RequestMethod.POST)
    public String antiBullyingCreate(@Valid @ModelAttribute("report") AntiBullyingReport report) {

        try {

            if (!report.isAnonymous()) {
                //FALTA AGAFAR L'USUARI!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

                logger.info("[" + new Object() {
                }.getClass().getEnclosingMethod().getName() + "] -  Session user successfully loaded");

                User user = userService.getUserById("1");
                report.setUser(user);
            }
            //Alertar responsable

            antiBullyingReportService.addAntiBullyingReport(report);
            return "redirect:/";
        } catch (Exception e) {
            logger.error("[" + new Object() {
            }.getClass().getEnclosingMethod().getName() + "] -  Error creating an anti-bullying report: " + e);

            return null;
        }
    }

    /**
     * Processes the petition to get to the procedure creation page.
     *
     * @return ModelAndView with the desired .jsp file and its required model & objects
     */
    @RequestMapping(value = "/user/procedures")
    public ModelAndView proceduresAccess() {

        //FALTA AGAFAR L'USUARI!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        try {
            logger.info("[" + new Object() {
            }.getClass().getEnclosingMethod().getName() + "] -  Session user successfully loaded");

            User user = userService.getAllUserProcedures();
            List<Procedure> procedures;
            List<Procedure> validProcedures = new ArrayList<>();

            if (user != null && user.getProcedures() != null) {
                procedures = user.getProcedures();
                Date actualDate = new Date();

                for (Procedure p : procedures) {
                    if (p.getLimitDate().compareTo(actualDate) < 0) {
                        p.setStatus(ProcedureStatus.CANCELLED);
                        procedureService.addProcedure(p);
                    } else {
                        validProcedures.add(p);
                    }
                }
            } else {
                logger.warn("[" + new Object() {
                }.getClass().getEnclosingMethod().getName() + "] -  User has no procedures");
            }
            ModelAndView model = new ModelAndView("/procedureUser");
            model.addObject("procedures", validProcedures);

            return model;
        } catch (Exception e) {
            logger.error("[" + new Object() {
            }.getClass().getEnclosingMethod().getName() + "] -  Error accessing the procedures: " + e);

            return null;
        }
    }

    /**
     * Processes the petition to get to the group creation page.
     *
     * @param teacherId the id of the teacher we want to access the profile of
     * @return ModelAndView with the desired .jsp file and its required model & objects
     */
    @RequestMapping(value = "/user/teacher/{teacherId}", method = RequestMethod.GET)
    public ModelAndView accessTeacherProfile(@PathVariable("teacherId") String teacherId) {
        try {
            ModelAndView model = new ModelAndView("/teacherProfile");
            RoleTeacher teacher = userService.getTeacherById(teacherId);

            logger.info("[" + new Object() {
            }.getClass().getEnclosingMethod().getName() + "] -  Teacher with id " + teacherId + " successfully found");

            Procedure procedure = new Procedure();
            procedure.setUserP(new User());
            model.addObject("teacher", teacher);
            model.addObject("procedure", procedure);
            return model;
        } catch (Exception e) {
            logger.error("[" + new Object() {
            }.getClass().getEnclosingMethod().getName() + "] -  Error finding the teacher with id " + teacherId + ": " + e);

            return null;
        }
    }

    /**
     * Processes the response of a procedure.
     *
     * @param id       the id of the procedure we want to response to
     * @param decision boolean that indicates if the user has accepted or denied the online procedure
     * @return returns the user to last visited page with an url
     */
    @RequestMapping(value = "/user/procedure/response")
    public String procedureCreate(@Valid @ModelAttribute("id") String id, @ModelAttribute("decision") boolean decision) {

        try {
            Procedure procedure = procedureService.findById(id);
            if (decision == true) procedure.setStatus(ProcedureStatus.ACCEPTED);
            else procedure.setStatus(ProcedureStatus.DENIED);

            procedureService.addProcedure(procedure);

            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String referer = request.getHeader("Referer");

            return "redirect:" + referer;
        } catch (Exception e) {
            logger.error("[" + new Object() {
            }.getClass().getEnclosingMethod().getName() + "] -  Error finding the procedure with id " + id + ": " + e);

            return null;
        }
    }
}
