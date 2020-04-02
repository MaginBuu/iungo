package com.model;


import com.model.enums.Department;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "role_teacher")
public class RoleTeacher extends RoleClass {

    @ManyToMany(mappedBy="teachers" , fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Subject> subjects = new LinkedList<>();

    @Column(name = "DEPARTMENT")
    public Department department;

    public RoleTeacher() {}

    public void addSubject(Subject subject){
        this.subjects.add(subject);
    }

    public Department getDepartment() { return department; }

    public void setDepartment(Department department) { this.department = department; }



}
