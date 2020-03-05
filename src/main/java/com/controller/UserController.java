package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.service.UserService;
import com.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.model.BillingAddress;
import com.model.Customer;
import com.model.ShippingAddress;
import com.model.User;
import com.service.CustomerService;

import java.util.List;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public UserService getCustomerService() {
		return userService;
	}

	public void setCustomerService(UserService userService) {
		this.userService = userService;
	}
	//private CustomerService customerService;

	//public CustomerService getCustomerService() {
	//	return customerService;
	//}

	//public void setCustomerService(CustomerService customerService) {
	//	this.customerService = customerService;
	//}

	@RequestMapping(value = "/user/tickets", method = RequestMethod.GET)
	public ModelAndView getUsers(){
		User user = userService.getAllUserTickets();
		return new ModelAndView("ticket", "user", user);
	}

	@RequestMapping(value = "/user/creation")
	public ModelAndView getRegistrationForm() {
		User user = new User();
		return new ModelAndView("register", "user", user);
	}

	// to insert the data
	@RequestMapping(value = "/user/creation", method = RequestMethod.POST)
	public String registerCustomer(@Valid @ModelAttribute(value = "user") User user, Model model,
			BindingResult result) {
		if (result.hasErrors())
			return "register";
		user.setPassword(passwordEncoder.encode("pass"));
		userService.addUser(user);
		//customerService.addCustomer(customer);
		model.addAttribute("registrationSuccess", "Registered Successfully. Login using username and password");
		return "redirect:/login";
	}


	@RequestMapping(value = "/postlogin", method = RequestMethod.GET)
	public String postLogin(HttpServletRequest request) {
		System.out.println("postlogin");
		System.out.println(request.getUserPrincipal().getName());
		User user = userService.getUserByEmail(request.getUserPrincipal().getName());
		request.getSession().setAttribute("name", user.getName());
		request.getSession().setAttribute("user", user);
		System.out.println(user);

		//customerService.addCustomer(customer);
		return "redirect:/";
	}
}
