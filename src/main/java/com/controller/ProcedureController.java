package com.controller;

import com.model.Procedure;
import com.model.User;
import com.service.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.ParseException;


@Controller
public class ProcedureController {

	@Autowired
	ProcedureService procedureService;


	@RequestMapping(value = "/procedure/creation")
	public ModelAndView getProcedureCreationForm() {
		Procedure procedure = new Procedure();
		return new ModelAndView("createProcedure", "procedure", procedure);
	}

	// to insert the data
	@RequestMapping(value = "/procedure/creation", method = RequestMethod.POST)
	public String createProcedure(@Valid @ModelAttribute(value = "procedure") Procedure procedure, Model model, BindingResult result) throws ParseException {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		User user = (User)request.getSession().getAttribute("user");

		procedureService.addProcedure(procedure);

		return "redirect:/";
	}
}
