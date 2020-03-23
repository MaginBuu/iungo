package com.service;

import com.dao.CourseDao;
import com.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseDao courseDao;

    public void addCourse(Course course){
        courseDao.addCourse(course);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseDao.getAllCourses();
    }

    @Override
    public Course findByDate(int date) {
        return courseDao.findByDate(date);
    }
}
