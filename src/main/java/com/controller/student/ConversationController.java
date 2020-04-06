package com.controller.student;

import com.model.*;
import com.model.enums.Role;
import com.service.ConversationService;
import com.service.UserService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@Controller
public class ConversationController {

    @Autowired
    UserService userService;

    @Autowired
    ConversationService conversationService;


    /**
     * Processes the petition to get to the conversation creation page.
     *
     * @return ModelAndView with the desired .jsp file and its required model & objects
     */
    @RequestMapping(value = "/conversation/creation")
    public ModelAndView getConversationCreationForm() {
        Conversation conversation = new Conversation();


        User user = userService.getUserById("1"); //this will be the logged user
        RoleStudent roleStudent = (RoleStudent) user.getRoleClass(Role.STUDENT);

        List<User> teachers = userService.getAllUsersWithRole(Role.TEACHER);
        List<User> students = userService.getStudentsByGroup(roleStudent.getGroup().getGroupId());

        students.remove(user);

        ModelAndView model = new ModelAndView("student/createConversation");
        model.addObject("conversation", new Conversation());
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
    public String createConversation(@Valid @ModelAttribute("conversation") Conversation conversation) throws ParseException {
        String[] users = conversation.getUsersTemp().split(",");

        for (String userId : users){
            conversation.addUser(userService.getUserById(userId));
        }
            conversation.addUser(userService.getUserById("1")); // this will be the logged user

        conversationService.addConversation(conversation);


        return "redirect:/";
    }

    @RequestMapping("/conversation/getMessages")
    public @ResponseBody
    JSONArray conversationAjax(@RequestParam("conversationId") String conversationId) {
        List<Message> messages;
        messages = conversationService.getWithMessages(conversationId).getMessages();

        JSONArray data = new JSONArray();
        for(Message m : messages){
            JSONObject o = new JSONObject();
            o.put("id", m.getMessageId());
            o.put("date", m.getDate().toString());
            o.put("subject", m.getSubject());
            o.put("body", m.getMessageBody());
            o.put("sender", m.getSender().getName()+" "+m.getSender().getSurname()+" "+m.getSender().getSecondSurname());

            data.add(o);
        }
        return data;
    }
}
