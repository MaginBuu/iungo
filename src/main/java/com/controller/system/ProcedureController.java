package com.controller.system;

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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Controller
public class ProcedureController {

	private static final Logger logger = LogManager.getLogger(GroupController.class);

	@Autowired
	ProcedureService procedureService;
	@Autowired
	UserService userService;

	/**
	 * Processes the petition to get to the procedure creation page.
	 *
	 * @return ModelAndView with the desired .jsp file and its required model & objects
	 */
	@RequestMapping(value = "/procedure/creation")
	public ModelAndView procedureCreationAccess() {
		try {
			ModelAndView model = new ModelAndView("system/createProcedure");
			Procedure procedure = new Procedure();
			procedure.setHour("23:59");
			procedure.setUserP(new User());
			model.addObject("procedure", procedure);
			model.addObject("users", userService.getAllUsers());

			return model;
		}catch (Exception e) {
			logger.error("[" + new Object() {
			}.getClass().getEnclosingMethod().getName() + "] -  Error accessing the procedure creation page: " + e);

			return null;
		}
	}

	/**
	 * Processes the petition to get to the procedure creation page.
	 *
	 * @return ModelAndView with the desired .jsp file and its required model & objects
	 */
	@RequestMapping(value = "/procedure/createMeetingRequest", method = RequestMethod.POST)
	public String createMeetingRequest(@Valid @ModelAttribute("procedure") Procedure procedure, BindingResult result, HttpServletRequest request) {

		try {
			procedure.setOnline(true);
			procedure.setCreationDate(new Date());
			User user = (User) request.getSession().getAttribute("user");

			logger.info("[" + new Object() {
			}.getClass().getEnclosingMethod().getName() + "] -  Session user successfully loaded");

			if (user == null)
				user = userService.getUserById("1");

			String title = "Meeting request from " + user.getName() + " " + user.getSurname();
			procedure.setTitle(title);

			procedure.setStatus(ProcedureStatus.CREATED);

			procedureService.addProcedure(procedure);
			logger.info("[" + new Object() {
			}.getClass().getEnclosingMethod().getName() + "] -  Meeting request (procedure) successfully created");

			return "redirect:/";
		}catch (Exception e) {
			logger.error("[" + new Object() {
			}.getClass().getEnclosingMethod().getName() + "] -  Error creating a meeting request (procedure): " + e);

			return null;
		}
	}

	/**
	 * Processes the creation of a new procedure by using all parameters from the "New Procedure" form.
	 *
	 * @param procedure the procedure with all its elements
	 * @return returns the user to the main page with an url
	 */
	@RequestMapping(value = "/procedure/creation", method = RequestMethod.POST)
	public String createProcedure(@Valid @ModelAttribute("procedure") Procedure procedure, BindingResult result, ModelMap model) throws ParseException {
		try {
			procedure.setStatus(ProcedureStatus.CREATED);
			procedure.setCreationDate(new Date());
			procedureService.addProcedure(procedure);
			logger.info("[" + new Object() {
			}.getClass().getEnclosingMethod().getName() + "] -  Meeting request (procedure) successfully created");

			return "redirect:/";
		}catch (Exception e) {
			logger.error("[" + new Object() {
			}.getClass().getEnclosingMethod().getName() + "] -  Error creating a procedure: " + e);

			return null;
		}
	}
}
