package com.model;


import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "courses")
public class Course {

    private String courseId;

    private Date StartDate;

    private Date endDate;
}
