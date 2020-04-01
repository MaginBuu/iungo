package com.controller;

import com.model.*;
import com.model.enums.TicketStatus;
import com.service.TicketService;
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

	@Autowired
	private TicketService ticketService;

	/**
	 * Processes the petition to get to the ticket creation page.
	 *
	 * @return ModelAndView with the desired .jsp file
	 */
	@RequestMapping(value = "/ticket/creation", method = RequestMethod.GET)
	public ModelAndView getTicketCreationForm() {
		Ticket ticket = new Ticket();
		return new ModelAndView("createTicket", "ticket", ticket);
	}

	/**
	 * Processes the creation of a new ticket by using all parameters from the "New Ticket" form.
	 *
	 * @param ticket the ticket with all its elements
	 * @return returns the user to the main page with an url
	 */
	@RequestMapping(value = "/ticket/creation", method = RequestMethod.POST)
	public String createTicket(@Valid @ModelAttribute(value = "ticket") Ticket ticket, Model model, BindingResult result) throws ParseException {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		User user = (User)request.getSession().getAttribute("user");

		ticket.setuser(user);
		ticket.setStatus(TicketStatus.CREATED);

		DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		ticket.setCreationDate(sdf.parse(sdf.format(date)));

		ticketService.addTicket(ticket);

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
		for(Ticket t : tickets){
			System.out.println(t.getTitle());
		}
		return new ModelAndView("ticketAdmin", "tickets", tickets);
	}

	/**
	 * Processes the petition to get to the ticket modification page.
	 *
	 * @param ticketId the id of the specific ticket to modify
	 * @return ModelAndView with the desired .jsp file and its required model & objects
	 */
	@RequestMapping(value = "/ticket/modify", method = RequestMethod.GET)
	public ModelAndView getTicketModify(@RequestParam String ticketId) {
			Ticket ticket = ticketService.getTicketById(ticketId);
			return new ModelAndView("updateTicket", "ticket", ticket);
	}

	/**
	 * Processes the update of a specific ticket by using all parameters from the "Modify Space" form.
	 *
	 * @param ticket the updated ticket with all its elements
	 * @return returns the user to the main page with an url
	 */
	@RequestMapping(value = "/ticket/modify", method = RequestMethod.POST)
	public String updateTicketModify(@Valid @ModelAttribute("ticket") Ticket ticket){
		Ticket outdatedTicket = ticketService.getTicketById(ticket.getTicketId());
		outdatedTicket.setStatus(ticket.getStatus());
		outdatedTicket.setAdminResponse(ticket.getAdminResponse());
		ticketService.updateTicket(outdatedTicket);
		return "redirect:/";
	}
}
