package com.controller;

import com.model.Subject;
import com.model.TimeLine;
import com.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;


@Controller
public class SubjectController {

	@Autowired
	SubjectService subjectService;


	@RequestMapping(value = "/subject/creation")
	public ModelAndView getProcedureCreationForm() {
		ModelAndView model = new ModelAndView("createSubject");
		Subject subject = new Subject();
		model.addObject("subject", subject);
		return model;
	}

	// to insert the data
	@RequestMapping(value = "/subject/creation", method = RequestMethod.POST)
	public String createProcedure(@Valid @ModelAttribute("subject") Subject subject, BindingResult result, ModelMap model) throws ParseException {
		//PER A FER
		return "redirect:/";
	}

	@RequestMapping(value = "/subject/modify", method = RequestMethod.GET)
	public ModelAndView getSubjectModify(@RequestParam String subjectId) {
		Subject subject = new Subject()/* = subjectService.getByIdWithAll(subjectId)*/;
		return new ModelAndView("updateSubject", "subject", subject);
	}

	@RequestMapping(value = "/subject/modify", method = RequestMethod.POST)
	public String updateSubjectModify(@Valid @ModelAttribute("subject") Subject subject){
		subjectService.addSubject(subject);
		return "redirect:/";
	}

	@RequestMapping(value = "/subject/add/timeline", method = RequestMethod.GET)
	public void addTimeline(){
		System.out.println("ADD");
	}
}
