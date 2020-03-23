package com.controller;


import com.model.Procedure;
import com.model.Ticket;
import com.model.User;
import com.model.enums.ProcedureStatus;
import com.service.ProcedureService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
public class ElementController {

    @RequestMapping(value = "/element/access", method = RequestMethod.GET)
    public ModelAndView getElementAccessForm() {
        ModelAndView model = new ModelAndView("findElement");
        String elementKind = "", id = "", name = "";
        model.addObject("elementKind", elementKind);
        //model.addObject("id", id);
        //model.addObject("name", name);
        return model;
    }

    @RequestMapping(value = "/element/find", method = RequestMethod.GET)
    public void findElement(@Valid @ModelAttribute("elementKind") String element) {
        //ModelAndView model = new ModelAndView("createProcedure");
        System.out.println(element);
        //return model;
    }

}
