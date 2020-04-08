package com.controller.user;

import com.model.Conversation;
import com.model.Message;
import com.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@Controller
public class UserTestController {

	@Autowired
	ConversationService conversationService;

	@RequestMapping(value = "/user/messages")
	public ModelAndView messages(){
		List<Conversation> conversations = conversationService.getAllConversations();
		Message message = new Message();
		message.setConversation(new Conversation());
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
		System.out.println(message.getSubject());
		System.out.println(message.getMessageBody());
		System.out.println(message.getConversation().getConversationId());


	}

}
