package com.model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "COURSE_ID")
    private String courseId;

    @Column(name = "START_DATE")
    private int StartDate;

    @Column(name = "END_DATE")
    private int endDate;

    @OneToMany
    @Column(name = "COURSE_GROUPS")
    private List<Group> groups;

    public int getStartDate() {
        return StartDate;
    }

    public void setStartDate(int startDate) {
        StartDate = startDate;
    }

    public int getEndDate() {
        return endDate;
    }

    public void setEndDate(int endDate) {
        this.endDate = endDate;
    }
}
