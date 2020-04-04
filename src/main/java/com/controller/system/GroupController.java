package com.controller.system;

import com.model.Course;
import com.model.ClassGroup;
import com.service.CourseService;
import com.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.util.Calendar;
import javax.validation.Valid;
import java.util.List;


@Controller
public class GroupController {

	@Autowired
	GroupService groupService;

	@Autowired
	CourseService courseService;

	/**
	 * Processes the petition to get to the group creation page.
	 *
	 * @return ModelAndView with the desired .jsp file and its required model & objects
	 */
	@RequestMapping(value = "/group/creation")
	public ModelAndView getGroupCreationForm() {

		List<Course> courses = courseService.getAllCourses();
		if(courses.size() == 0){
			int year = Calendar.getInstance().get(Calendar.YEAR);
			for(int i = 0; i < 4; i++){
				Course course = new Course(year-1, year);
				courses.add(course);
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
	}

	/**
	 * Processes the creation of a new group by using all parameters from the "New Group" form.
	 *
	 * @param group the group with all its elements
	 * @return returns the user to the main page with an url
	 */
	@RequestMapping(value = "/group/creation", method = RequestMethod.POST)
	public String createGroup(@Valid @ModelAttribute("group") ClassGroup group) {

		if(group.getCourse() == null){
			int year = Calendar.getInstance().get(Calendar.YEAR); // Gets the actual year
			Course course = courseService.findByDate(year-1); // Looks for a date one less, as the course starts by the smaller year (2019-2020)
			if (course == null){
				course = new Course(year-1, year); // It generates a new course
				courseService.addCourse(course);

			}
			group.setCourse(course);
		}

		groupService.addGroup(group);

		return "redirect:/";
	}
}
