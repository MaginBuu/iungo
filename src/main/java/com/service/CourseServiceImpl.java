package com.service;

import com.dao.CourseDao;
import com.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseDao courseDao;

    public void addCourse(Course course){
        courseDao.addCourse(course);
    }
}
