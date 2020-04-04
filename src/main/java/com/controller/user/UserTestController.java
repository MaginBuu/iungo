package com.controller.user;

import com.model.Conversation;
import com.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserTestController {

	@Autowired
	ConversationService conversationService;

	@RequestMapping(value = "/user/messages")
	public ModelAndView messages(){
		List<Conversation> conversations = conversationService.getAllConversations();
		ModelAndView model = new ModelAndView("/message");
		model.addObject("conversations", conversations);
		return model;
	}
}
