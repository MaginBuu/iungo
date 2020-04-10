package com.controller.user;

import com.model.*;
import com.model.enums.ProcedureStatus;
import com.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.SQLOutput;
import java.text.ParseException;

import com.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.service.ConversationService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
public class UserTestController {

	@Autowired
	ConversationService conversationService;

	@Autowired
	AntiBullyingReportService antiBullyingReportService;

	@Autowired
	UserService userService;

	@Autowired
	ProcedureService procedureService;
	
	@Autowired
	MessageService messageService;

	@Autowired
	ConversationUserService conversationUserService;



	@RequestMapping(value = "/user/messages")
	public ModelAndView messages(HttpServletRequest request){
		Message message = new Message();


		User user = (User)request.getSession().getAttribute("user");
		List<Conversation> conversations;
		if(user == null)
			conversations = conversationService.findAllConversationsByUserId("1");
		else
			conversations = conversationService.findAllConversationsByUserId(user.getUserId());

		Collections.sort(conversations);

		for(Conversation conversation : conversations){
			conversation.setUnread(conversationUserService.findUnread(user.getUserId(), conversation.getConversationId()));
		}

		ModelAndView model = new ModelAndView("/message");
		model.addObject("conversations", conversations);
		model.addObject("message", message);
		return model;
	}


	/**
	 * Processes the creation of a new ticket by using all parameters from the "New Ticket" form.
	 *
	 * @param message the message with all its elements
	 * @return returns the user to the main page with an url
	 */
	@RequestMapping(value = "/message/creation", method = RequestMethod.POST)
	public String createMessage(@Valid @ModelAttribute(value = "message") Message message,  BindingResult result, HttpServletRequest request) {

		User user = (User)request.getSession().getAttribute("user");

		if(user == null)
			message.setSender(userService.getUserById("1"));
		else
			message.setSender(user);

		message.setDate(new Date());

		messageService.addMessage(message);

		Conversation conversation = conversationService.getWithUsers(message.getConversation().getConversationId());
		conversation.setLastMessageDate(new Date());
		conversationService.addConversation(conversation);

		List<ConversationUser> conversationUsers = conversation.getUserConversations();

		for(ConversationUser cu : conversationUsers){
			if (!cu.getUser().equals(user)){
				cu.newMessage();
				conversationUserService.addConversationUser(cu);
			}
		}


		String referer = request.getHeader("Referer");

		return "redirect:" + referer;


	}

	@RequestMapping(value = "/user/antibullying")
	public ModelAndView antiBullyingAccess(@RequestParam Boolean observed){
		//FALTA AGAFAR L'USUARI!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

		AntiBullyingReport newReport = new AntiBullyingReport();
		newReport.setObserved(observed);
		ModelAndView model = new ModelAndView("/createReport");
		model.addObject("report", newReport);

		return model;
	}//anonymously

	@RequestMapping(value = "/user/antibullying/report", method = RequestMethod.POST)
	public String antiBullyingCreate(@Valid @ModelAttribute("report") AntiBullyingReport report) {

		if(!report.isAnonymous()){
			//FALTA AGAFAR L'USUARI!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			User user = userService.getUserById("1");
			report.setUser(user);
		}
		//Alertar responsable

		antiBullyingReportService.addAntiBullyingReport(report);
		return "redirect:/";
	}

	@RequestMapping(value = "/user/procedures")
	public ModelAndView proceduresAccess(){

		User user = userService.getAllUserProcedures();
		List<Procedure> procedures;
		List<Procedure> validProcedures = new ArrayList<>();

		if(user != null && user.getProcedures() != null) {
			procedures = user.getProcedures();
			Date actualDate = new Date();

			for (Procedure p : procedures) {
				if (p.getLimitDate().compareTo(actualDate) < 0) {
					p.setStatus(ProcedureStatus.CANCELLED);
					procedureService.addProcedure(p);
				} else {
					validProcedures.add(p);
				}
			}
		}
		ModelAndView model = new ModelAndView("/procedureUser");
		model.addObject("procedures", validProcedures);

		return model;
	}//anonymously

	@RequestMapping(value = "/user/teacher/{teacherId}", method = RequestMethod.GET)
	public ModelAndView accessTeacherProfile(@PathVariable("teacherId") String teacherId){
		System.out.println(teacherId);
		ModelAndView model = new ModelAndView("/teacherProfile");
		RoleTeacher teacher = userService.getTeacherById(teacherId);
		System.out.println(teacher);
		model.addObject("teacher",teacher);
		return model;
	}

	@RequestMapping(value = "/user/procedure/response")
	public String antiBullyingCreate(@Valid @ModelAttribute("id") String id, @ModelAttribute("decision") boolean decision) {

		Procedure procedure = procedureService.findById(id);
		if(decision == true) procedure.setStatus(ProcedureStatus.ACCEPTED);
		else procedure.setStatus(ProcedureStatus.DENIED);

		procedureService.addProcedure(procedure);

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String referer = request.getHeader("Referer");

		return "redirect:" + referer;
	}
}
