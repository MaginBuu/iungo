package com.controller;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import antlr.StringUtils;
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

import java.util.LinkedList;
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

		//Set username
		String username;
		if(!user.getSecondSurname().equals("")){
			username = user.getName().substring(0, Math.min(user.getName().length(), 2))
						+ user.getSurname().substring(0, Math.min(user.getSurname().length(), 2))
						+ user.getSecondSurname().substring(0, Math.min(user.getSecondSurname().length(), 2));
		}else{
			username = user.getName().substring(0, Math.min(user.getName().length(), 3))
						+ user.getSurname().substring(0, Math.min(user.getSurname().length(), 3));
		}


		List<String> usernames = userService.getAllUsernames(username);

		if(usernames.size() == 1 && usernames.get(0).equals(username)){
			username += String.format("%02d", 1);
		}else if (usernames.size() > 1){
			String lastUsername = usernames.get(usernames.size() - 1);
			String number = lastUsername.substring(lastUsername.length() - 2);
			if (number.matches("[0-9]+")) {
				int numTemp = Integer.parseInt(number);
				numTemp++;
				username += String.format("%02d", numTemp);
			}
		}

		user.setUsername(username);

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



		if(roles[0].equals(Role.STUDENT)){
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			request.getSession().setAttribute("userNameRelation", username);
			return "redirect:/user/creation/relateChildResponsible";}


		return "redirect:/";
	}

	@RequestMapping(value = "/user/delete", method = RequestMethod.GET)
	public String deleteSpace(@RequestParam String userId){
		userService.deleteUser(userService.getUserById(userId));

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String referer = request.getHeader("Referer");

		return "redirect:" + referer;
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
	@RequestMapping(value = "/user/creation/relateChildResponsible")
	public ModelAndView getProcedureCreationForm() {
		ModelAndView model = new ModelAndView("selectChild");
		model.addObject("users", userService.getAllUsersWithRole(Role.RESPONSIBLE));

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
