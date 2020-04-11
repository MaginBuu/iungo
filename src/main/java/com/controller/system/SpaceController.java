package com.controller.system;

import com.model.Space;
import com.service.SpaceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Controller
public class SpaceController {
	private static final Logger logger = LogManager.getLogger(SpaceController.class);


	@Autowired
	SpaceService spaceService;

	/**
	 * Processes the petition to get to the space creation page.
	 *
	 * @return ModelAndView with the desired .jsp file and its required model & objects
	 */
	@RequestMapping(value = "/space/creation")
	public ModelAndView procedureCreationFormAccess() {
		try {
			ModelAndView model = new ModelAndView("system/createSpace");
			Space space = new Space();
			model.addObject("space", space);
			return model;
		}catch (Exception e) {
			logger.error("["+new Object(){}.getClass().getEnclosingMethod().getName()+"] -  Error accessing the procedure form creation: "+e);
			return null;
		}
	}

	/**
	 * Processes the creation of a new space by using all parameters from the "New Space" form.
	 *
	 * @param space the space with all its elements
	 * @return returns the user to the main page with an url
	 */
	@RequestMapping(value = "/space/creation", method = RequestMethod.POST)
	public String createSpace(@Valid @ModelAttribute("space") Space space) {

		try{
			if(space.getName() == null) logger.warn("["+new Object(){}.getClass().getEnclosingMethod().getName()+"] -  Space has no attribute 'NAME'");
			if(space.getCapacity() == 0) logger.warn("["+new Object(){}.getClass().getEnclosingMethod().getName()+"] -  Space has the attribute 'CAPACITY' value set to zero");
			if(space.getTypology() == null) logger.warn("["+new Object(){}.getClass().getEnclosingMethod().getName()+"] -  Space has no attribute 'TYPOLOGY'");
			spaceService.addSpace(space);
			logger.info("["+new Object(){}.getClass().getEnclosingMethod().getName()+"] -  Space successfully created");
		} catch (Exception e){
			logger.error("["+new Object(){}.getClass().getEnclosingMethod().getName()+"] -  Error creating a new space: "+e);
		}
		return "redirect:/";
	}

	/**
	 * Processes the petition to get to the space modification page.
	 *
	 * @param spaceId the id of the specific space to modify
	 * @return ModelAndView with the desired .jsp file and its required model & objects
	 */
	@RequestMapping(value = "/space/modify", method = RequestMethod.GET)
	public ModelAndView getSpaceModify(@RequestParam String spaceId) {

		Space space = new Space();
		try{
			space = spaceService.getByIdWithTimeline(spaceId);
			logger.info("["+new Object(){}.getClass().getEnclosingMethod().getName()+"] -  Space with id=" +spaceId + " successfully loaded: ");
		} catch (Exception e){
			logger.error("["+new Object(){}.getClass().getEnclosingMethod().getName()+"] -  Space with id=" +spaceId + " was not found ");
		}

		return new ModelAndView("system/updateSpace", "space", space);
	}

	/**
	 * Processes the update of a specific space by using all parameters from the "Modify Space" form.
	 *
	 * @param space the updated space with all its elements
	 * @return returns the user to the main page with an url
	 */
	@RequestMapping(value = "/space/modify", method = RequestMethod.POST)
	public String updateSpaceModify(@Valid @ModelAttribute("space") Space space){
		try{
			spaceService.addSpace(space);
			logger.info("["+new Object(){}.getClass().getEnclosingMethod().getName()+"] -  Space sucessfully updated ");
		} catch (Exception e){
			logger.error("["+new Object(){}.getClass().getEnclosingMethod().getName()+"] -  failed to save space");
		}
		return "redirect:/";
	}

	/**
	 * Processes the removal of a specific space.
	 *
	 * @param spaceId the id of the space to delete
	 * @return returns the user to the previous page with an url
	 */
	@RequestMapping(value = "/space/delete", method = RequestMethod.GET)
	public String deleteSpace(@RequestParam String spaceId){
		try{
			spaceService.deleteSpace(spaceService.getById(spaceId));
			logger.info("["+new Object(){}.getClass().getEnclosingMethod().getName()+"] -  Space sucessfully removed");
		}catch (Exception e){
			logger.error("["+new Object(){}.getClass().getEnclosingMethod().getName()+"] -  failed to delete space");
		}

		//Getting the referer page
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String referer = request.getHeader("Referer");

		return "redirect:" + referer;
	}
}
