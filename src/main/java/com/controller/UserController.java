package com.controller;

import javax.validation.Valid;

import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

	@RequestMapping(value = "/user/get", method = RequestMethod.GET)
	public ModelAndView getUsers(){
		List<User> users = userService.getAllUsers();
		return new ModelAndView("userList", "users", users);
	}

	@RequestMapping(value = "/customer/registration")
	public ModelAndView getRegistrationForm() {
		Customer customer = new Customer();
		User user = new User();
		BillingAddress ba = new BillingAddress();
		ShippingAddress sa = new ShippingAddress();
		customer.setShippingAddress(sa);
		customer.setBillingAddress(ba);
		customer.setUsers(user);

		return new ModelAndView("register", "user", user);
	}

	// to insert the data
	@RequestMapping(value = "/customer/registration", method = RequestMethod.POST)
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
