package com.controller;

import com.model.Ticket;
import com.model.User;
import com.model.enums.TicketStatus;
import com.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class TicketController {

	@Autowired
	private TicketService ticketService;

	@RequestMapping(value = "/ticket/creation")
	public ModelAndView getRegistrationForm() {
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
}
