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
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.text.ParseException;


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
		String attributes = space.getAttributesTemp();

		setAttributes(space, attributes);

		spaceService.addSpace(space);
		return "redirect:/";
	}

	private void setAttributes(Space space, String attributes){
		if(attributes.contains("Blackboard"))
			space.setBlackboard(true);
		else
			space.setBlackboard(false);

		if(attributes.contains("Interior"))
			space.setInterior(true);
		else
			space.setInterior(false);

		if(attributes.contains("Projector"))
			space.setProjector(true);
		else
			space.setProjector(false);

		if(attributes.contains("Platform"))
			space.setPlatform(true);
		else
			space.setPlatform(false);

		if(attributes.contains("Tables"))
			space.setTables(true);
		else
			space.setTables(false);
	}
}
