package com.controller.user;

import com.model.AntiBullyingReport;
import com.model.Conversation;
import com.model.Message;
import com.model.User;
import com.service.AntiBullyingReportService;
import com.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserTestController {

	@Autowired
	ConversationService conversationService;

	@Autowired
	AntiBullyingReportService antiBullyingReportService;

	@RequestMapping(value = "/user/messages")
	public ModelAndView messages(){
		//FALTA AGAFAR L'USUARI!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


		List<Conversation> conversations = conversationService.findAllConversationsByUserId("1");
		ModelAndView model = new ModelAndView("/message");
		model.addObject("conversations", conversations);
		return model;
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
	public String antiBullyingCreate(@Valid @ModelAttribute("report") AntiBullyingReport report, @RequestParam("anonymously") Boolean anonymously) {

		if(!anonymously){
			//FALTA AGAFAR L'USUARI!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

		}
		//Alertar responsable
		antiBullyingReportService.addAntiBullyingReport(report);
		return "redirect:/";
	}
}
