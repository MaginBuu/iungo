package com.controller.student;

import com.model.*;
import com.model.enums.Role;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ConversationController {

    @Autowired
    UserService userService;


    /**
     * Processes the petition to get to the conversation creation page.
     *
     * @return ModelAndView with the desired .jsp file and its required model & objects
     */
    @RequestMapping(value = "/conversation/creation")
    public ModelAndView getConversationCreationForm() {
        Conversation conversation = new Conversation();


        User user = userService.getUserById("1");
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
     * Processes the petition to get to the conversation creation page.
     *
     * @return ModelAndView with the desired .jsp file and its required model & objects
     */
    @RequestMapping(value = "/conversation/creation", method = RequestMethod.POST)
    public ModelAndView createConversation() {
        Conversation conversation = new Conversation();


        User user = userService.getUserById("1");
        RoleStudent roleStudent = (RoleStudent) user.getRoleClass(Role.STUDENT);

        List<User> teachers = userService.getAllUsersWithRole(Role.TEACHER);
        List<User> students = userService.getStudentsByGroup(roleStudent.getGroup().getGroupId());

        students.remove(user);


        ModelAndView model = new ModelAndView("student/createConversation");
        model.addObject("teachers", teachers);
        model.addObject("students", students);
        return model;
    }
}
