package com.model;


import com.model.enums.Department;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "role_teacher")
@NamedQueries({
        @NamedQuery(name = "RoleTeacher.findById", query = "SELECT o FROM RoleTeacher o WHERE o.userR.userId = :id"),
        @NamedQuery(name = "RoleTeacher.findByIdWithTimelines", query = "SELECT o FROM RoleTeacher o LEFT JOIN FETCH o.timelines i WHERE o.userR.userId = :id"),
        @NamedQuery(name = "RoleTeacher.findByIdWithSubjects", query = "SELECT o FROM RoleTeacher o LEFT JOIN FETCH o.subjects i WHERE o.userR.userId = :id AND i.name <> 'consultation_hours'"),
        @NamedQuery(name = "RoleTeacher.getAll", query ="SELECT o.userR FROM RoleTeacher o"),
        @NamedQuery(name = "RoleTeacher.findAll", query = "SELECT o FROM RoleTeacher o"),
})
public class RoleTeacher extends RoleClass {

    @ManyToMany(mappedBy="teachers", fetch=FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Subject> subjects = new LinkedList<>();

    @Column(name = "DEPARTMENT")
    public Department department;

    @OneToMany(mappedBy="teacher", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TimeLine> timelines;

    public RoleTeacher() {}

    public void addSubject(Subject subject){
        this.subjects.add(subject);
    }

    public Department getDepartment() { return department; }

    public void setDepartment(Department department) { this.department = department; }

    public List<TimeLine> getTimelines() {
        return timelines;
    }

    public void setTimelines(List<TimeLine> timelines) {
        this.timelines = timelines;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
}
