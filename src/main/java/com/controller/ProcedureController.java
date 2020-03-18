package com.controller;

import com.controller.PropertyEditor.UserProcedurePropertyEditor;
import com.model.Procedure;
import com.model.Ticket;
import com.model.enums.ProcedureStatus;
import com.service.ProcedureService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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


	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, "procedure.userP",
				new UserProcedurePropertyEditor());
	}

	@RequestMapping(value = "/procedure/creation")
	public ModelAndView getProcedureCreationForm() {
		ModelAndView model = new ModelAndView("createProcedure");
		model.addObject("procedure", new Procedure());
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

		procedureService.addProcedure(procedure);
		return "redirect:/";
	}
}
