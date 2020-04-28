package com.model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "courses")
@NamedQueries({
        @NamedQuery(name = "Courses.findAll", query = "SELECT c FROM Course c"),
        @NamedQuery(name = "Courses.findByStart", query = "SELECT r FROM Course r WHERE r.startDate = :startDate"),

})
public class Course {

    private static final long serialVersionUID = 2681531852204068105L;

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "COURSE_ID")
    private String courseId;

    @Column(name = "START_DATE")
    private int startDate;

    @Column(name = "END_DATE")
    private int endDate;

    @OneToMany(mappedBy="course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Evaluation> evaluations;

    public Course(int startDate, int endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Course() {
    }

    public int getStartDate() {
        return startDate;
    }

    public void setStartDate(int startDate) {
        startDate = startDate;
    }

    public int getEndDate() {
        return endDate;
    }

    public void setEndDate(int endDate) {
        this.endDate = endDate;
    }

    public String getCourseId() { return courseId; }

    public void setCourseId(String courseId) { this.courseId = courseId; }
}
