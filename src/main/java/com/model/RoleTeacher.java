package com.model;


import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "role_teacher")
public class RoleTeacher extends RoleClass {

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "teacher_subjects")
    private List<Subject> subjects = new LinkedList<>();

    public RoleTeacher() {}


}
