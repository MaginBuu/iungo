package com.controller.user;

import com.model.*;
import com.model.enums.Role;
import com.service.ConversationService;
import com.service.ConversationUserService;
import com.service.UserService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Controller
public class ConversationController {

    private static final Logger logger = LogManager.getLogger(ConversationController.class);

    @Autowired
    UserService userService;

    @Autowired
    ConversationService conversationService;

    @Autowired
    ConversationUserService conversationUserService;


    /**
     * Processes the petition to get to the conversation creation page.
     *
     * @return ModelAndView with the desired .jsp file and its required model & objects
     */
    @RequestMapping(value = "/conversation/creation")
    public ModelAndView getConversationCreationForm(HttpServletRequest request, Authentication authentication) {

        User user;
        List<User> teachers;
        List<User> students = new LinkedList<>();
        List<User> admins = new LinkedList<>();
        List<User> secretaries = new LinkedList<>();
        List<User> responsibles = new LinkedList<>();
        try{
            user = (User) request.getSession().getAttribute("user");
            String role;
            if(user==null){
                user = userService.getUserById("1");
                role = "ADMIN";
            }else
                role = authentication.getAuthorities().toArray()[0].toString();

            logger.info("["+new Object(){}.getClass().getEnclosingMethod().getName()+"] -  Session user successfully loaded: " + user.getUserId() + " with role " + role);


            if ("STUDENT".equals(role)) {

                RoleStudent roleStudent = (RoleStudent) user.getRoleClass(Role.STUDENT);
                teachers = userService.getAllUsersWithRole(Role.TEACHER);
                students = userService.getStudentsByGroup(roleStudent.getGroup().getGroupId());
                students.remove(user);

                logger.info("["+new Object(){}.getClass().getEnclosingMethod().getName()+"] -  Student list loaded successfully from group " + roleStudent.getGroup().getGroupId());

            } else if ("RESPONSIBLE".equals(role)) {

                teachers = userService.getAllUsersWithRole(Role.TEACHER);
                teachers.remove(user);
                logger.info("["+new Object(){}.getClass().getEnclosingMethod().getName()+"] -  Teacher list loaded successfully");


            } else {
                students = userService.getAllUsersWithRole(Role.STUDENT);
                teachers = userService.getAllUsersWithRole(Role.TEACHER);
                teachers.remove(user);
                admins = userService.getAllUsersWithRole(Role.ADMIN);
                admins.remove(user);
                secretaries = userService.getAllUsersWithRole(Role.SECRETARY);
                secretaries.remove(user);
                responsibles = userService.getAllUsersWithRole(Role.RESPONSIBLE);
                responsibles.remove(user);

                logger.info("["+new Object(){}.getClass().getEnclosingMethod().getName()+"] -  Receiver list successfully loaded");
            }

            ModelAndView model = new ModelAndView("createConversation");
            model.addObject("conversation", new Conversation());

            model.addObject("responsibles", responsibles);
            model.addObject("admins", admins);
            model.addObject("secretaries", secretaries);
            model.addObject("teachers", teachers);
            model.addObject("students", students);

            logger.info("["+new Object(){}.getClass().getEnclosingMethod().getName()+"] -  Conversation successfully loaded ");

            return model;
        }catch (Exception e) {
            logger.error("["+new Object(){}.getClass().getEnclosingMethod().getName()+"] -  Error processing the conversation load: "+e);

            return null;
        }
    }


    /**
     * Processes the creation of a new procedure by using all parameters from the "New Procedure" form.
     *
     * @param conversation the procedure with all its elements
     * @return returns the user to the main page with an url
     */
    @RequestMapping(value = "/conversation/creation", method = RequestMethod.POST)
    public String createConversation(@Valid @ModelAttribute("conversation") Conversation conversation, HttpServletRequest request) {
        String[] users = conversation.getUsersTemp().split(",");
        conversation.setLastMessageDate(new Date());
        try {
            conversationService.addConversation(conversation);

            for (String userId : users) {
                ConversationUser conversationUser = new ConversationUser(userService.getUserById(userId), conversation, new Date());
                conversationUserService.addConversationUser(conversationUser);
                conversation.addUserConversations(conversationUser);

                logger.info("[" + new Object() {
                }.getClass().getEnclosingMethod().getName() + "] -  Conversation successfully created for user " + userId);
            }

            User user = (User) request.getSession().getAttribute("user");
            if(user==null)
                user = userService.getUserById("1");

            logger.info("[" + new Object() {
            }.getClass().getEnclosingMethod().getName() + "] -  Session user successfully loaded: " + user.getUserId());

            ConversationUser conversationUser = new ConversationUser(user, conversation, new Date());
            conversationUserService.addConversationUser(conversationUser);

            logger.info("[" + new Object() {
            }.getClass().getEnclosingMethod().getName() + "] -  Conversation successfully created for the session user");

            return "redirect:/user/messages";
        }catch (Exception e) {
            logger.error("["+new Object(){}.getClass().getEnclosingMethod().getName()+"] -  Error processing the conversation creation: "+e);

            return null;
        }
    }

    @RequestMapping("/conversation/getMessages")
    public @ResponseBody
    JSONObject conversationLoadMessages(@RequestParam("conversationId") String conversationId, HttpServletRequest request) {

        try {
            User user = (User) request.getSession().getAttribute("user");

            logger.info("[" + new Object() {
            }.getClass().getEnclosingMethod().getName() + "] -  Session user successfully loaded");

            ConversationUser conversationUser;

            if (user == null) //this is for testing, will be deleted
                conversationUser = conversationUserService.findByUserAndConversation("1", conversationId);
            else
                conversationUser = conversationUserService.findByUserAndConversation(user.getUserId(), conversationId);

            Date last = conversationUser.getLastVisit();

            conversationUser.setLastVisit(new Date());
            conversationUser.messagesReaded();
            conversationUserService.addConversationUser(conversationUser);

            List<Message> messages;
            messages = conversationService.getWithMessages(conversationId).getMessages();
            JSONArray data = new JSONArray();
            if(messages == null) {
                logger.warn("["+new Object(){}.getClass().getEnclosingMethod().getName()+"] -  Conversation has no messages");
            }
            else{
                for (Message m : messages) {
                    JSONObject o = new JSONObject();
                    o.put("id", m.getMessageId());
                    o.put("date", m.getDate());
                    o.put("stringDate", m.getDate().toString());
                    o.put("subject", m.getSubject());
                    o.put("body", m.getMessageBody());
                    o.put("sender", m.getSender().getName() + " " + m.getSender().getSurname() + " " + m.getSender().getSecondSurname());

                    data.add(o);
                }
            }

            JSONObject allMessages = new JSONObject();
            allMessages.put("last", last);
            allMessages.put("msg", data);
            return allMessages;

        }catch (Exception e) {
            logger.error("["+new Object(){}.getClass().getEnclosingMethod().getName()+"] -  Error getting the messages: "+e);

            return null;
        }
    }

    @RequestMapping(value = "/conversation/delete", method = RequestMethod.GET)
    public String deleteConversation(@RequestParam String conversationId, HttpServletRequest request){

        try {
            User user = (User)request.getSession().getAttribute("user");
            if(user == null)
                user = userService.getUserById("1");
            ConversationUser conversationUser = conversationUserService.findByUserAndConversation(user.getUserId(), conversationId);
            conversationUserService.deleteConversationUser(conversationUser);

            logger.info("[" + new Object() {
            }.getClass().getEnclosingMethod().getName() + "] -  Conversation "+conversationId+" successfully deleted");

            String referer = request.getHeader("Referer");

            return "redirect:" + referer;

        }catch (Exception e) {
            logger.error("["+new Object(){}.getClass().getEnclosingMethod().getName()+"] -  Error deleting conversation "+conversationId+": "+e);

            return null;
        }
    }
}
