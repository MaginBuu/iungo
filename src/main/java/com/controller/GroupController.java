package com.controller;

import com.model.Course;
import com.model.ClassGroup;
import com.service.CourseService;
import com.service.GroupService;
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
public class GroupController {

	@Autowired
	GroupService groupService;

	@Autowired
	CourseService courseService;


	@RequestMapping(value = "/group/creation")
	public ModelAndView getGroupCreationForm() {

		List<Course> courses = courseService.getAllCourses();
		if(courses.size() == 0){
			int year = 2018;
			for(int i = 0; i < 4; i++){
				year += 1;
				Course course = new Course(year, year + 1);
				courses.add(course);
				courseService.addCourse(course);
			}
		}
		ModelAndView model = new ModelAndView("createGroup");
		ClassGroup group = new ClassGroup();
		group.setCourse(new Course());
		model.addObject("group", new ClassGroup());
		model.addObject("courses", courses);
		return model;
	}

	// to insert the data
	@RequestMapping(value = "/group/creation", method = RequestMethod.POST)
	public String createGroup(@Valid @ModelAttribute("group") ClassGroup group, BindingResult result, ModelMap model) throws ParseException {

		if(group.getCourse() == null){
			System.out.println("null");
			group.setCourse(courseService.findByDate(2019));}

		groupService.addGroup(group);

		return "redirect:/";
	}
}
