package com.service;

import com.model.Course;

import java.util.List;

public interface CourseService {

    void addCourse(Course course);

    List<Course> getAllCourses();

    Course findByDate(int date);
}
