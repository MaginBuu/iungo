package com.controller;


import com.model.*;
import com.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;


@Controller
public class ElementController {

    @Autowired
    ElementService elementService;

    @Autowired
    UserService userService;

    @Autowired
    GroupService groupService;

    @Autowired
    SpaceService spaceService;

    @Autowired
    SubjectService subjectService;

    @RequestMapping(value = "/element/access", method = RequestMethod.GET)
    public ModelAndView getElementAccessForm() {
        ModelAndView model = new ModelAndView("findElement");
        String elementKind = "", idNumber = "", name = "";
        model.addObject("elementKind", elementKind);
        model.addObject("idNumber", idNumber);
        model.addObject("name", name);
        return model;
    }

    @RequestMapping(value = "/element/find", method = RequestMethod.GET)
    public ModelAndView findElement(@Valid @ModelAttribute("elementKind") String element, @ModelAttribute("idNumber")
            String id, @ModelAttribute("name") String name) {

        String query = elementService.generateQuery(element, id, name);
        ModelAndView model;
        switch (element){
            case "group":
                model = new ModelAndView("listGroupSearch");
                List<Group> groups = groupService.getQueryResults(query);
                model.addObject("groups", groups);
                break;

            case "space":
                model = new ModelAndView("listSpaceSearch");
                List<Space> spaces = spaceService.getQueryResults(query);
                model.addObject("spaces", spaces);
                break;

            case "subject":
                model = new ModelAndView("listSubjectSearch");
                List<Subject> subjects = subjectService.getQueryResults(query);
                for(Subject s: subjects){
                    System.out.println(s.getSubjectGroup());
                }
                model.addObject("subjects", subjects);
                break;
            default: //user
                model = new ModelAndView("listProfileSearch");
                List<User> profiles = userService.getQueryResults(query);
                model.addObject("profiles", profiles);
                break;
        }
        System.out.println(query);
        return model;
    }


}
