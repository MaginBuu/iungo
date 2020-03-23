package com.controller;

import com.model.Group;
import com.model.Procedure;
import com.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.text.ParseException;



@Controller
public class GroupController {

	@Autowired
	GroupService groupService;


	@RequestMapping(value = "/group/creation")
	public ModelAndView getProcedureCreationForm() {
		return new ModelAndView("createGroup", "group", new Group());
	}

	// to insert the data
	@RequestMapping(value = "/group/creation", method = RequestMethod.POST)
	public String createProcedure(@Valid @ModelAttribute("group") Group group, BindingResult result, ModelMap model) throws ParseException {

		groupService.addGroup(group);

		return "redirect:/";
	}
}
