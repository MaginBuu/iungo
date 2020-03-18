package com.controller;

import com.model.Procedure;
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

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
public class ProcedureController {

	@Autowired
	ProcedureService procedureService;
	@Autowired
	UserService userService;


	@RequestMapping(value = "/procedure/creation")
	public ModelAndView getProcedureCreationForm() {
		ModelAndView model = new ModelAndView("createProcedure");
		Procedure procedure = new Procedure();
		procedure.setUserP(new User());
		model.addObject("procedure", procedure);
		model.addObject("users", userService.getAllUsers());
		return model;
	}

	// to insert the data
	@RequestMapping(value = "/procedure/creation", method = RequestMethod.POST)
	public String createProcedure(@Valid @ModelAttribute("procedure") Procedure procedure, BindingResult result, ModelMap model) throws ParseException {
		procedure.setStatus(ProcedureStatus.CREATED);
		DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		procedure.setCreationDate(sdf.parse(sdf.format(date)));
		procedure.setLimitDate(sdf.parse(sdf.format(date)));
		procedure.setUserP(userService.getUserById(procedure.getUserP().getUserId()));

		procedureService.addProcedure(procedure);
		return "redirect:/";
	}
}
