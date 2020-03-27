package com.controller;

import com.model.Space;
import com.model.Subject;
import com.model.TimeLine;
import com.service.SpaceService;
import com.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.json.simple.JSONObject;


import javax.validation.Valid;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;


@Controller
public class SubjectController {

	@Autowired
	SubjectService subjectService;

	@Autowired
	SpaceService spaceService;


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

	@RequestMapping(value = "/subject/modify/{subjectId}", method = RequestMethod.GET)
	public ModelAndView getSubjectModify(@PathVariable String subjectId) {
		Subject subject = subjectService.getByIdWithAll(subjectId);
		return new ModelAndView("updateSubject", "subject", subject);
	}

	@RequestMapping(value = "/subject/modify", method = RequestMethod.POST)
	public ModelAndView updateSubjectModify(@Valid @ModelAttribute("subject") Subject subject, @ModelAttribute("buttonName")
			String selection){
		subjectService.addSubject(subject);
		ModelAndView model;
		switch (selection){
			case "add":
				model = new ModelAndView("addTimeline");
				List<Space> spaces = spaceService.getAll();
				Space bookSpace = new Space();
				model.addObject("subject", subject);
				model.addObject("spaces", spaces);
				model.addObject("bookSpace", bookSpace);
				break;
			default:
				subjectService.addSubject(subject);
				model = new ModelAndView("updateSubject", "subject", subject);
				break;
		}

		return model;

	}

	@RequestMapping(value = "/subject/add/timeline", method = RequestMethod.GET)
	public void addTimeline(@Valid @ModelAttribute("subject") Subject subject){
		System.out.println("ADD");
		System.out.println(subject.getName());
	}

	@RequestMapping("/ajax")
	public ModelAndView helloAjaxTest() {
		System.out.println("ENTRA");
		ModelAndView model = new ModelAndView("tabela");
		Subject subject = subjectService.getByIdWithAll("1");
		model.addObject("subject", subject);
		return model;
	}

	@RequestMapping("/subject/ajaxdos")
	public @ResponseBody List<String> peeenis(@RequestParam("var1") String var1) {
		List<String> list = Arrays.asList("foo", "bar");
		return list;
	}
}
