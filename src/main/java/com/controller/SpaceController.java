package com.controller;

import com.model.Space;
import com.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import java.text.ParseException;
import java.util.List;


@Controller
public class SpaceController {

	@Autowired
	SpaceService spaceService;


	@RequestMapping(value = "/space/creation")
	public ModelAndView getProcedureCreationForm() {
		ModelAndView model = new ModelAndView("createSpace");
		Space space = new Space();
		model.addObject("space", space);
		return model;
	}

	// to insert the data
	@RequestMapping(value = "/space/creation", method = RequestMethod.POST)
	public String createProcedure(@Valid @ModelAttribute("space") Space space, BindingResult result, ModelMap model) throws ParseException {

		spaceService.addSpace(space);
		return "redirect:/";
	}

	@RequestMapping(value = "/space/modify", method = RequestMethod.GET)
	public ModelAndView getSpaceModify(@RequestParam String spaceId) {
		Space space = spaceService.getById(spaceId);
		return new ModelAndView("updateSpace", "space", space);
	}

	@RequestMapping(value = "/space/modify", method = RequestMethod.POST)
	public String updateSpaceModify(@Valid @ModelAttribute("space") Space space){
		spaceService.addSpace(space);
		return "redirect:/";
	}

	@RequestMapping(value = "/space/delete", method = RequestMethod.GET)
	public String deleteSpace(@RequestParam String spaceId){
		System.out.println("delete " + spaceId);
		spaceService.deleteSpace(spaceService.getById(spaceId));

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String referer = request.getHeader("Referer");

		return "redirect:" + referer;
	}

	@RequestMapping(value = "/space/add/timeline", method = RequestMethod.GET)
	public void addTimeline(){
		System.out.println("ADD");
	}

	public List<Space> getAll(){
		return spaceService.getAll();
	}
}
