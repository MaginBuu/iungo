package com.controller.user;

import com.model.Subject;
import com.service.SubjectService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class SubjectControllerUser {

    private static final Logger logger = LogManager.getLogger(SubjectControllerUser.class);

    @Autowired
    SubjectService subjectService;

    @RequestMapping(value = "/user/subject/{subjectId}", method = RequestMethod.GET)
    public ModelAndView accessTeacherProfile(@PathVariable("subjectId") String subjectId) {

        Subject subject = subjectService.getByIdWithChapters(subjectId);
        System.out.println();


        return new ModelAndView("/user/showSubject");
    }

}
