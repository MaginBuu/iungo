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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Controller
public class ConversationController {

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

        User user = (User)request.getSession().getAttribute("user");
        String role = authentication.getAuthorities().toArray()[0].toString();


        List<User> teachers;
        List<User> students = new LinkedList<>();
        List<User> admins = new LinkedList<>();
        List<User> secretaries = new LinkedList<>();
        List<User> responsibles = new LinkedList<>();


        if("STUDENT".equals(role)){

            RoleStudent roleStudent = (RoleStudent) user.getRoleClass(Role.STUDENT);
            teachers = userService.getAllUsersWithRole(Role.TEACHER);
            students = userService.getStudentsByGroup(roleStudent.getGroup().getGroupId());
            students.remove(user);

        }else if("RESPONSIBLE".equals(role)){

            teachers = userService.getAllUsersWithRole(Role.TEACHER);

        }else{

            students = userService.getAllUsersWithRole(Role.STUDENT);
            teachers = userService.getAllUsersWithRole(Role.TEACHER);
            admins = userService.getAllUsersWithRole(Role.ADMIN);
            secretaries = userService.getAllUsersWithRole(Role.SECRETARY);
            responsibles = userService.getAllUsersWithRole(Role.RESPONSIBLE);

            switch (role){
                case "ADMIN":
                    admins.remove(user);
                    break;

                case "SECRETARY":
                    secretaries.remove(user);
                    break;

                default:
                    teachers.remove(user);
                    break;
            }

        }


        ModelAndView model = new ModelAndView("createConversation");
        model.addObject("conversation", new Conversation());

        model.addObject("responsibles", responsibles);
        model.addObject("admins", admins);
        model.addObject("secretaries", secretaries);
        model.addObject("teachers", teachers);
        model.addObject("students", students);
        return model;
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
        conversationService.addConversation(conversation);

        for (String userId : users){
            ConversationUser conversationUser = new ConversationUser(userService.getUserById(userId), conversation, new Date());
            conversationUserService.addConversationUser(conversationUser);
            conversation.addUserConversations(conversationUser);
        }

        User user = (User)request.getSession().getAttribute("user");
        if(user == null){ //this is for testing, will be deleted
            user = userService.getUserById("1");
        }
        ConversationUser conversationUser = new ConversationUser(user, conversation, new Date());
        conversationUserService.addConversationUser(conversationUser);

        return "redirect:/user/messages";
    }

    @RequestMapping("/conversation/getMessages")
    public @ResponseBody
    JSONObject conversationLoadMessages(@RequestParam("conversationId") String conversationId, HttpServletRequest request) {

        User user = (User)request.getSession().getAttribute("user");

        ConversationUser conversationUser;

        if(user == null) //this is for testing, will be deleted
            conversationUser = conversationUserService.findByUserAndConversation("1", conversationId);
        else
            conversationUser = conversationUserService.findByUserAndConversation(user.getUserId(),conversationId);

        Date last = conversationUser.getLastVisit();

        conversationUser.setLastVisit(new Date());
        conversationUser.messagesReaded();
        conversationUserService.addConversationUser(conversationUser);

        List<Message> messages;
        messages = conversationService.getWithMessages(conversationId).getMessages();

        JSONArray data = new JSONArray();
        for(Message m : messages){
            JSONObject o = new JSONObject();
            o.put("id", m.getMessageId());
            o.put("date", m.getDate());
            o.put("stringDate", m.getDate().toString());
            o.put("subject", m.getSubject());
            o.put("body", m.getMessageBody());
            o.put("sender", m.getSender().getName()+" "+m.getSender().getSurname()+" "+m.getSender().getSecondSurname());

            data.add(o);
        }
        JSONObject pepe = new JSONObject();
        pepe.put("last",last);
        pepe.put("msg",data);
        return pepe;
    }

    @RequestMapping(value = "/conversation/delete", method = RequestMethod.GET)
    public String deleteConversation(@RequestParam String conversationId){

        //FALTA AGAFAR L'USUARI!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        ConversationUser conversationUser = conversationUserService.findByUserAndConversation("1",conversationId);
        conversationUserService.deleteConversationUser(conversationUser);

        System.out.println("deleted");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String referer = request.getHeader("Referer");

        return "redirect:" + referer;
    }
}
