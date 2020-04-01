package com.model;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "role_teacher")
public class RoleTeacher extends RoleClass {

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "teacher_subjects")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Subject> subjects = new LinkedList<>();

    public RoleTeacher() {}

    public void addSubject(Subject subject){
        this.subjects.add(subject);
    }


}
