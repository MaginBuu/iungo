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

    /**
     * Processes the petition to navigate to the "Edit Element".
     *
     * @return the ModelAndView with its objects
     */
    @RequestMapping(value = "/element/access", method = RequestMethod.GET)
    public ModelAndView getElementAccessForm() {
        ModelAndView model = new ModelAndView("findElement");
        String elementKind = "", idNumber = "", name = "";
        model.addObject("elementKind", elementKind); //The kind of the element(s) to look for
        model.addObject("idNumber", idNumber); //The name or strings contained in the name of the element(s) to look for
        model.addObject("name", name); //If known, the id of the element to look for
        return model;
    }

    /**
     * Processes the petition to navigate to the list of elements with the desired constraints.
     * It calls for a query in order to look for the possible elements existing in the data base.
     *
     * @param element kind of the element(s) to look for
     * @param name name, or strings contained in the name of the element(s) to look for
     * @param id id of the element to look for
     * @return the ModelAndView
     */
    @RequestMapping(value = "/element/find", method = RequestMethod.GET)
    public ModelAndView findElement(@Valid @ModelAttribute("elementKind") String element, @ModelAttribute("idNumber")
            String id, @ModelAttribute("name") String name) {

        String query = elementService.generateQuery(element, id, name);
        ModelAndView model;
        switch (element){
            case "group":
                model = new ModelAndView("listGroupSearch");
                List<ClassGroup> groups = groupService.getQueryResults(query);
                model.addObject("groups", groups); //List of all groups found
                break;

            case "space":
                model = new ModelAndView("listSpaceSearch");
                List<Space> spaces = spaceService.getQueryResults(query);
                model.addObject("spaces", spaces); //List of all spaces found
                break;

            case "subject":
                model = new ModelAndView("listSubjectSearch");
                List<Subject> subjects = subjectService.getQueryResults(query);
                model.addObject("subjects", subjects); //Similar here...
                break;

            default: //User
                model = new ModelAndView("listProfileSearch");
                List<User> profiles = userService.getQueryResults(query);
                model.addObject("profiles", profiles); //And here...
                break;
        }
        return model;
    }


}
