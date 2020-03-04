package com.controller;

import com.model.Ticket;
import com.model.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class TicketController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/ticket/creation")
	public ModelAndView getRegistrationForm() {
		Ticket ticket = new Ticket();
		return new ModelAndView("createTicket", "ticket", ticket);
	}

	// to insert the data
	@RequestMapping(value = "/ticket/creation", method = RequestMethod.POST)
	public String registerCustomer(@Valid @ModelAttribute(value = "user") User user, Model model,
			BindingResult result) {
		if (result.hasErrors())
			return "register";
		userService.addUser(user);
		//customerService.addCustomer(customer);
		model.addAttribute("registrationSuccess", "Registered Successfully. Login using username and password");
		return "redirect:/login";
	}
}
