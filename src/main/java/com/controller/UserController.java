package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.model.*;
import com.model.enums.Role;
import com.service.RoleService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

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
		return new ModelAndView("ticketUser", "user", user);
	}

	@RequestMapping(value = "/user/creation", method = RequestMethod.GET)
	public ModelAndView getTicketCreationForm() {
		User user = new User();
		return new ModelAndView("createUser", "user", user);
	}

	// to insert the data
	@RequestMapping(value = "/user/creation", method = RequestMethod.POST)
	public String registerCustomer(@Valid @ModelAttribute(value = "user") User user, Model model,
			BindingResult result) {
		if (result.hasErrors())
			return "createUser";
		user.setPassword(passwordEncoder.encode("pass"));

		String roles[] = user.getRole().split(",");

		for (String role : roles){
			RoleClass roleClass = null;
			if(role.equals("STUDENT"))
				roleClass = new RoleStudent();
			else if(role.equals("RESPONSIBLE"))
				roleClass = new RoleResponsible();
			else if(role.equals("TEACHER"))
				roleClass = new RoleTeacher();
			else if(role.equals("SECRETARY"))
				roleClass = new RoleSecretary();
			else
				roleClass = new RoleAdmin();

			roleClass.setUserR(user);
			roleClass.setRoleKey(Role.valueOf(role));
			user.addRole(Role.valueOf(role), roleClass);
		}

		userService.addUser(user);


		Authorities authorities = new Authorities();
		authorities.setAuthorities(roles[roles.length-1]);
		authorities.setEmailId(user.getEmailId());
		userService.addAuthorities(authorities);

		model.addAttribute("registrationSuccess", "Registered Successfully. Login using username and password");
		return "redirect:/";
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


	@RequestMapping(value = "/test/admin")
	public ModelAndView searchTest() {
		List<User> users = userService.getAllUsers();
		return new ModelAndView("ticketAdmin", "users", users);
	}

	@RequestMapping(value = "/user/creation/selectChild")
	public ModelAndView getProcedureCreationForm() {
		ModelAndView model = new ModelAndView("selectChild");
		model.addObject("users", userService.getAllUsers());

		return model;
	}


	@RequestMapping(value = "/user/creation/selectChild", method = RequestMethod.POST)
	public String findElement(@Valid @ModelAttribute("child") String child){
		System.out.println(child);
		return "redirect:/";
	}

}
