package com.controller.system;

import com.model.*;
import com.model.enums.TicketStatus;
import com.service.TicketService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class TicketController {

	private static final Logger logger = LogManager.getLogger(SubjectController.class);

	@Autowired
	private TicketService ticketService;

	/**
	 * Processes the petition to get to the ticket creation page.
	 *
	 * @return ModelAndView with the desired .jsp file
	 */
	@RequestMapping(value = "/ticket/creation", method = RequestMethod.GET)
	public ModelAndView TicketCreationFormAccess() {
		try {

			Ticket ticket = new Ticket();
			return new ModelAndView("system/createTicket", "ticket", ticket);
		}catch (Exception e){
			logger.error("["+new Object(){}.getClass().getEnclosingMethod().getName()+"] -  Error accessing the Ticket Creation Form page: "+e);
			return null;
		}
	}

	/**
	 * Processes the creation of a new ticket by using all parameters from the "New Ticket" form.
	 *
	 * @param ticket the ticket with all its elements
	 * @return returns the user to the main page with an url
	 */
	@RequestMapping(value = "/ticket/creation", method = RequestMethod.POST)
	public String createTicket(@Valid @ModelAttribute(value = "ticket") Ticket ticket) throws ParseException {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		User user = (User)request.getSession().getAttribute("user");

		logger.info("[" + new Object() {
		}.getClass().getEnclosingMethod().getName() + "] -  Session user successfully loaded");

		ticket.setuser(user);
		ticket.setStatus(TicketStatus.CREATED);

		DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		ticket.setCreationDate(sdf.parse(sdf.format(date)));

		try {
			ticketService.addTicket(ticket);
		}catch (Exception e){
			logger.error("["+new Object(){}.getClass().getEnclosingMethod().getName()+"] -  Error creating the new ticket: "+e);
			return null;
		}

		return "redirect:/";
	}

	/**
	 * Processes the petition to get to the ticket history page.
	 *
	 * @return ModelAndView with the desired .jsp file
	 */
	@RequestMapping(value = "/ticket/access", method = RequestMethod.GET)
	public ModelAndView getTicketAccessForm() {
			List<Ticket> tickets = ticketService.getOngoingCreatedTickets();
			return new ModelAndView("system/ticketAdmin", "tickets", tickets);

	}

	/**
	 * Processes the petition to get to the ticket modification page.
	 *
	 * @param ticketId the id of the specific ticket to modify
	 * @return ModelAndView with the desired .jsp file and its required model & objects
	 */
	@RequestMapping(value = "/ticket/modify", method = RequestMethod.GET)
	public ModelAndView getTicketModify(@RequestParam String ticketId) {
		try {
			Ticket ticket = ticketService.getTicketById(ticketId);
			return new ModelAndView("system/updateTicket", "ticket", ticket);
		}catch (Exception e){
			logger.error("["+new Object(){}.getClass().getEnclosingMethod().getName()+"] -  Error accessing the ticket, ID may not exist: "+e);
			return null;
		}
	}

	/**
	 * Processes the update of a specific ticket by using all parameters from the "Modify Space" form.
	 *
	 * @param ticket the updated ticket with all its elements
	 * @return returns the user to the main page with an url
	 */
	@RequestMapping(value = "/ticket/modify", method = RequestMethod.POST)
	public String updateTicketModify(@Valid @ModelAttribute("ticket") Ticket ticket){
		try {
			Ticket outdatedTicket = ticketService.getTicketById(ticket.getTicketId());
			outdatedTicket.setStatus(ticket.getStatus());
			outdatedTicket.setAdminResponse(ticket.getAdminResponse());
			ticketService.updateTicket(outdatedTicket);
			logger.info("["+new Object(){}.getClass().getEnclosingMethod().getName()+"] -  Ticket "+ticket.getTicketId()+" updated successfully");

		}catch (Exception e){
			logger.error("["+new Object(){}.getClass().getEnclosingMethod().getName()+"] -  Error updating the ticket: "+e);
		}

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}
}
