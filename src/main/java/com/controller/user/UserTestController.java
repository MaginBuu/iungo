package com.controller.user;

import com.model.*;
import com.model.enums.ProcedureStatus;
import com.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
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

	@RequestMapping(value = "/user/messages")
	public ModelAndView messages(){
		Message message = new Message();
		message.setConversation(new Conversation());
		//FALTA AGAFAR L'USUARI!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


		List<Conversation> conversations = conversationService.findAllConversationsByUserId("1");
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
	public void createTicket(@Valid @ModelAttribute(value = "message") Message message, BindingResult result, ModelMap model) {

		/*
		List<ConversationUser> conversationUsersList = message.getConversation().getUserConversations();
		for(ConversationUser cu : conversationUsersList){
			if("1".equals(cu.getUser().getUserId())) cu.setUnread(true); //Aqui comparariem la id amb la de l'usuari
		}
		*/

		System.out.println(message.getSubject());
		System.out.println(message.getMessageBody());
		System.out.println(message.getConversation().getConversationId());


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

	@RequestMapping(value = "/user/ticket", method = RequestMethod.GET)
	public ModelAndView getUsllers(){
		User user = userService.getAllUserTickets();
		return new ModelAndView("ticketUser", "user", user);
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
