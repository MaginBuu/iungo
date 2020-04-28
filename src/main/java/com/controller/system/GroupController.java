package com.controller.system;

import com.model.Course;
import com.model.ClassGroup;
import com.model.Evaluation;
import com.model.enums.Term;
import com.service.CourseService;
import com.service.EvaluationService;
import com.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.util.Calendar;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Controller
public class GroupController {

	private static final Logger logger = LogManager.getLogger(GroupController.class);

	@Autowired
	GroupService groupService;

	@Autowired
	CourseService courseService;

	@Autowired
	EvaluationService evaluationService;

	/**
	 * Processes the petition to get to the group creation page.
	 *
	 * @return ModelAndView with the desired .jsp file and its required model & objects
	 */
	@RequestMapping(value = "/group/creation")
	public ModelAndView getGroupCreationForm() {

		try {
			List<Course> courses = courseService.getAllCourses();
			if (courses.size() == 0) {
				int year = Calendar.getInstance().get(Calendar.YEAR);
				for (int i = 0; i < 4; i++) {
					Course course = new Course(year - 1, year);
					courses.add(course);
					for(int u = 0; i<4; u++){
						Evaluation e = new Evaluation();
						e.setTerm(Term.values()[u]);
						e.setVisibilityDate(new Date());
					}
					evaluationService.addEvaluation(new Evaluation());
					courseService.addCourse(course);
					year += 1;
				}
			}
			ModelAndView model = new ModelAndView("system/createGroup");
			ClassGroup group = new ClassGroup();
			group.setCourse(new Course());
			model.addObject("group", new ClassGroup());
			model.addObject("courses", courses);
			return model;
		}catch (Exception e) {
			logger.error("[" + new Object() {
			}.getClass().getEnclosingMethod().getName() + "] -  Error accessing the group creation page, data base may not have any course: " + e);

			return null;
		}
	}

	/**
	 * Processes the creation of a new group by using all parameters from the "New Group" form.
	 *
	 * @param group the group with all its elements
	 * @return returns the user to the main page with an url
	 */
	@RequestMapping(value = "/group/creation", method = RequestMethod.POST)
	public String createGroup(@Valid @ModelAttribute("group") ClassGroup group) {

		try {
			if (group.getCourse() == null) {
				int year = Calendar.getInstance().get(Calendar.YEAR); // Gets the actual year
				Course course = courseService.findByDate(year - 1); // Looks for a date one less, as the course starts by the smaller year (2019-2020)
				if (course == null) {
					course = new Course(year - 1, year); // It generates a new course
					courseService.addCourse(course);
					logger.info("[" + new Object() {
					}.getClass().getEnclosingMethod().getName() + "] -  Successfully created the new course");
				}
				group.setCourse(course);
			}

			groupService.addGroup(group);

			return "redirect:/";
		}catch (Exception e) {
			logger.error("[" + new Object() {
			}.getClass().getEnclosingMethod().getName() + "] -  Error creating the new course: " + e);

			return null;
		}
	}
}
