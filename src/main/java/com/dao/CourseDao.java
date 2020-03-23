package com.dao;

import com.model.Course;

import java.util.List;

public interface CourseDao {

    void addCourse(Course course);

    List<Course> getAllCourses();

    Course findByDate(int date);
}
