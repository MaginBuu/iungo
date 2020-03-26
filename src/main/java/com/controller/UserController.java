package com.controller;

import javax.servlet.ServletOutputStream;
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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
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

	/**
	 * This function saves a user in the db with the fields of the jsp.
	 * This funcion also creates a new entry in the authorities table, requiered for springSecurity
	 *
	 * @param user to be saved into the db.
	 *             In the jsp are introduced var: name, surname, surname2, birthDate, email, role.
	 *             The password is always the same and is encripted: pass.
	 *             The controller parses the roles and create the specific class for each and introduce it into the
	 *             		user.roles, which is a hashmap
	 *
	 * @param model
	 * @param result
	 * @return it redirects to the path "/" (index)
	 */
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
		//the authority saved is the last one as it is the one that allow the user to do more things
		authorities.setAuthorities(roles[roles.length-1]);
		authorities.setEmailId(user.getEmailId());
		userService.addAuthorities(authorities);


		return "redirect:/";
	}

	/**
	 *
	 * After log in springSecurity redirects to this path,
	 * 		used to save full information of the user as a session variable
	 * @param request
	 * @return it redirects to the path "/" (index)
	 */
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


	// FOR TESTING, WILL BE DELETED SOON
	@RequestMapping(value = "/user/creation/selectChild")
	public ModelAndView getProcedureCreationForm() {
		ModelAndView model = new ModelAndView("selectChild");
		model.addObject("users", userService.getAllUsersWithRole(Role.STUDENT));

		return model;
	}


	// FOR TESTING, WILL BE DELETED SOON
	@RequestMapping(value = "/user/creation/selectChild", method = RequestMethod.POST)
	public String findElement(@Valid @ModelAttribute("child") String child){
		System.out.println(child);
		User userResponsible = userService.getUserById("ff8080817111975101711197a00a0000");
		User userChild = userService.getUserById(child);
		System.out.println("creating relationship");
		System.out.println(userChild.setParentalRelationship(userResponsible));
		userService.addUser(userChild);
		userService.addUser(userResponsible);
		return "redirect:/";
	}

}
