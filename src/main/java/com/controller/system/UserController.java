package com.controller.system;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.model.*;
import com.model.enums.Role;
import com.service.GroupService;
import com.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {

	private static final Logger logger = LogManager.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private GroupService groupService;

	@Autowired
	private PasswordEncoder passwordEncoder;


	@RequestMapping(value = "/user/tickets", method = RequestMethod.GET)
	public ModelAndView getUsers(){
		User user = userService.getAllUserTickets();
		logger.info("["+new Object(){}.getClass().getEnclosingMethod().getName()+"] -  Users loaded");
		return new ModelAndView("ticketUser", "user", user);
	}

	@RequestMapping(value = "/user/creation", method = RequestMethod.GET)
	public ModelAndView getTicketCreationForm() {
		User user = new User();
		return new ModelAndView("system/createUser", "user", user);
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
			return "system/createUser";
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

		username = username.toLowerCase();

		//search all similar usernames

		List<String> usernames = userService.getAllUsernames(username);

		//if username exist, add a number at the end to difference between users
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


		//set roles
		String roles[] = user.getRole().split(",");

		for (String role : roles){
			RoleClass roleClass = null;
			if(role.equals("STUDENT"))
				roleClass = new RoleStudent();
			else if(role.equals("RESPONSIBLE"))
				roleClass = new RoleResponsible();
			else if(role.equals("TEACHER")) {
				RoleTeacher roleTeacher = new RoleTeacher();
				if (!"".equals(user.getDepartment()))
					roleTeacher.setDepartment(user.getDepartment());
				roleClass = roleTeacher;
			}else if(role.equals("SECRETARY"))
				roleClass = new RoleSecretary();
			else
				roleClass = new RoleAdmin();

			roleClass.setUserR(user);
			roleClass.setRoleKey(Role.valueOf(role));
			user.addRole(Role.valueOf(role), roleClass);
		}

		userService.addUser(user);


		//set authorities
		Authorities authorities = new Authorities();
		//the authority saved is the last one as it is the one that allow the user to do more things
		authorities.setAuthorities(roles[roles.length-1]);
		authorities.setEmailId(user.getEmailId());
		userService.addAuthorities(authorities);


		//check if this user will relate with a child
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();


		if(request.getSession().getAttribute("userRelate") != null && !request.getSession().getAttribute("userRelate").equals("")){
			return "redirect:/user/creation/relateResponsible";
		}

		if(roles[0].equals("STUDENT")){
			request.getSession().setAttribute("userRelate", user.getUserId());
			request.getSession().setAttribute("userRelateName", user.getName() + " " + user.getSurname() + " " +user.getSecondSurname());
			return "redirect:/user/creation/relateResponsible";}


		return "redirect:/";
	}



	@RequestMapping(value = "/user/delete", method = RequestMethod.GET)
	public String deleteSpace(@RequestParam String userId){
		userService.deleteUser(userService.getUserById(userId));

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String referer = request.getHeader("Referer");

		return "redirect:" + referer;
	}


	@RequestMapping(value = "/test/admin")
	public ModelAndView searchTest() {
		List<User> users = userService.getAllUsers();
		return new ModelAndView("system/ticketAdmin", "users", users);
	}


	@RequestMapping(value = "/user/creation/relateResponsible", method = RequestMethod.GET)
	public ModelAndView relateUsers(HttpServletRequest request){
		String userId = (String)request.getSession().getAttribute("userRelate");
		ModelAndView model = new ModelAndView("system/selectResponsible");
		RoleStudent roleStudent = userService.getStudentWithResponsibles("22");
		model.addObject("responsibles", roleStudent.getResponsibles());
		return model;
	}

	@RequestMapping(value = "/user/searchResponsible", method = RequestMethod.GET)
	public ModelAndView searchResponsible(HttpServletRequest request){
		ModelAndView model = new ModelAndView("system/searchResponsible");
		return model;
	}



	/**
	 * Processes the petition to get to the subject modification page.
	 *
	 * @param userId the id of the specific user to modify
	 * @return ModelAndView with the desired .jsp file and its required model & objects
	 */
	@RequestMapping(value = "/user/modify", method = RequestMethod.GET)
	public ModelAndView getSubjectModify(@RequestParam String userId) {
		User user = userService.getUserById(userId);
		Set<Role> keyset = user.getRoles().keySet();
		String aux = "";
		for (Role role : keyset)
			aux += role.toString() + ",";
		aux = aux.substring(0, aux.length()-1);
		user.setRole(aux);
		System.out.println(user.getRole());
		return new ModelAndView("system/updateUser", "user", user);
	}


	@RequestMapping(value = "/user/creation/setParentalRelationship")
	public String seParentalRelationship(@RequestParam String responsibles){


		//get child and delete session var
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String childUsername = (String)request.getSession().getAttribute("userRelate");
		User userChild = userService.getUserById(childUsername);


		//get responsibles
		String responsiblesSplitted[] = responsibles.split(",");

		//set teachers to subject
		for (String id : responsiblesSplitted){
			User user = userService.getUserById(id);
			userChild.setParentalRelationship(user);
		}

		userService.addUser(userChild); // addUser = add or update
		return "redirect:/user/creation/relateGroup";
	}

	@RequestMapping(value = "/user/creation/relateGroup", method = RequestMethod.GET)
	public ModelAndView relateGroup(){
		ModelAndView model = new ModelAndView("system/relateGroupWithStudent");
		model.addObject("groups", groupService.getAllClassGroup());
		return model;
	}

	// FOR TESTING, WILL BE DELETED SOON
	@RequestMapping(value = "/user/creation/setStudentGroupRelation")
	public String setStudentGroupRelation(@RequestParam String groupId){


		//get child and delete session var
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String userId = (String)request.getSession().getAttribute("userRelate");
		request.getSession().removeAttribute("userRelate");
		request.getSession().removeAttribute("userRelateName");


		User student = userService.getUserById(userId);

		student.setGroup(groupService.getClassGroupById(groupId));

		userService.addUser(student); // addUser = add or update
		return "redirect:/";
	}


	@RequestMapping(value = "/user/roles", method = RequestMethod.GET)
	public @ResponseBody
	JSONArray getRoles(HttpServletRequest request, Authentication authentication) {
		System.out.println();
		User activeUser = (User) request.getSession().getAttribute("user");
		if(activeUser == null) activeUser = userService.getUserById("1");

		logger.info("[" + new Object() {
		}.getClass().getEnclosingMethod().getName() + "] -  Session user successfully loaded");

		Set<Role> roles = new HashSet<>(activeUser.getRoles().keySet());
		System.out.println();

		try {
			String role = authentication.getAuthorities().toArray()[0].toString();
			roles.remove(Role.valueOf(role));

		}catch (Exception e){
			logger.error("["+new Object(){}.getClass().getEnclosingMethod().getName()+"] -  Users authorities could not be loaded: " + e);

		}

		JSONArray data = new JSONArray();

		for(Role role : roles)
			data.add(role.toString());



		return data;
	}

	// FOR TESTING, WILL BE DELETED SOON
	@RequestMapping(value = "/user/role")
	public String setRole(@RequestParam String role){


		logger.info("["+new Object(){}.getClass().getEnclosingMethod().getName()+"] -  Role received: " + role);

		List<SimpleGrantedAuthority> updatedAuthorities = new LinkedList<>();
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
		updatedAuthorities.add(authority);

		SecurityContextHolder.getContext().setAuthentication(
				new UsernamePasswordAuthenticationToken(
						SecurityContextHolder.getContext().getAuthentication().getPrincipal(),
						SecurityContextHolder.getContext().getAuthentication().getCredentials(),
						updatedAuthorities)
		);

		return "redirect:/role";
	}

}
