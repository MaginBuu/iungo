package com.controller.user;

import com.model.*;
import com.service.GroupService;
import com.service.SubjectService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ResponsibleController {

    @Autowired
    UserService userService;

    @Autowired
    GroupService groupService;

    @Autowired
    SubjectService subjectService;

    @RequestMapping(value = "/responsible/{childId}/subjects", method = RequestMethod.GET)
    public ModelAndView accessChildSubjectList(@PathVariable("childId") String childId, HttpServletRequest request) {

        ModelAndView model = new ModelAndView("/userSubjects");
        RoleStudent rs = userService.getStudentByUserId(childId);

        try{
            request.getSession().removeAttribute("child");
        }catch (Exception e){
            System.out.printf(e.toString());
        }
        request.getSession().setAttribute("child", rs.getUserR());

        List<Subject> subjectList = subjectService.getByGroupNoTeachers(rs.getGroup().getGroupId());
        model.addObject("subjects", subjectList);
        return model;
    }

    @RequestMapping(value = "/responsible/{childId}/subjects/{subjectId}", method = RequestMethod.GET)
    public ModelAndView accessChildSubject(@PathVariable("childId") String childId, @PathVariable("subjectId") String subjectId) {
        ModelAndView model = new ModelAndView("/evaluateStudent");

        return model;
    }

    @RequestMapping(value = "/responsible/{childId}/profile", method = RequestMethod.GET)
    public ModelAndView accessChildProfile(@PathVariable("childId") String childId) {
        ModelAndView model = new ModelAndView("/user/studentProfile");
        RoleStudent rs = userService.getStudentByUserId(childId);
        /*List<Subject> subjectList = subjectService.getByGroupNoTeachers(rs.getGroup().getGroupId());
        model.addObject("subjects", subjectList);
        model.addObject("childId", childId);*/
        User u = userService.getUserById(rs.getUserR().getUserId());
        model.addObject("user",u);
        model.addObject("responsibles", rs.getResponsibles());
        model.addObject("message", new Message());
        return model;
    }


}
