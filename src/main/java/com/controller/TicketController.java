package com.controller;

import com.dao.TicketDaoImpl;
import com.model.Procedure;
import com.model.Ticket;
import com.model.User;
import com.model.enums.TicketStatus;
import com.service.ProcedureService;
import com.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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

	@RequestMapping(value = "/ticket/creation", method = RequestMethod.GET)
	public ModelAndView getTicketCreationForm() {
		Ticket ticket = new Ticket();
		return new ModelAndView("createTicket", "ticket", ticket);
	}

	// to insert the data
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

	@RequestMapping(value = "/ticket/access", method = RequestMethod.GET)
	public ModelAndView getTicketAccessForm() {
		List<Ticket> tickets = ticketService.getOngoingCreatedTickets();
		for(Ticket t : tickets){
			System.out.println(t.getTitle());
		}
		return new ModelAndView("ticketAdmin", "tickets", tickets);
	}

	@RequestMapping(value = "/ticket/modify", method = RequestMethod.GET)
	public ModelAndView getTicketModify(@RequestParam String ticketId) {
			Ticket ticket = ticketService.getTicketById(ticketId);
			return new ModelAndView("updateTicket", "ticket", ticket);
	}

	@RequestMapping(value = "/ticket/modify", method = RequestMethod.POST)
	public String updateTicketModify(@Valid @ModelAttribute("ticket") Ticket ticket){
		Ticket outdatedTicket = ticketService.getTicketById(ticket.getTicketId());
		outdatedTicket.setStatus(ticket.getStatus());
		outdatedTicket.setAdminResponse(ticket.getAdminResponse());
		ticketService.updateTicket(outdatedTicket);
		return "redirect:/";
	}
}
