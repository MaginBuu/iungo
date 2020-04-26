package com.model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "role_student")
@NamedQueries({
        @NamedQuery(name = "RoleStudent.getResponsibles", query ="SELECT o.responsibles FROM RoleStudent o WHERE o.userR.userId =:userId "),
        @NamedQuery(name = "RoleStudent.getAll", query ="SELECT o.userR FROM RoleStudent o"),
        @NamedQuery(name = "RoleStudent.getWithParents", query ="SELECT o FROM RoleStudent o LEFT JOIN FETCH o.responsibles WHERE o.userR.userId =:userId"),

})

public class RoleStudent extends RoleClass {

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "GROUP_ID")
    private ClassGroup group;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "families")
    private List<RoleResponsible> responsibles = new LinkedList<>();

    @OneToMany(mappedBy = "student")
    private List<UserTask> userTasks;

    @OneToMany(mappedBy = "student")
    private List<UserSubject> userSubjects;

    public RoleStudent() { }

    public ClassGroup getGroup() { return group; }

    public void setGroup(ClassGroup group) { this.group = group; }

    public List<RoleResponsible> getResponsibles() { return responsibles; }

    public void setResponsibles(List<RoleResponsible> responsibles) { this.responsibles = responsibles; }

    public void addResponsible(RoleResponsible responsible){
        this.responsibles.remove(responsible);
        this.responsibles.add(responsible);
    }

    public List<UserTask> getUserTasks() { return userTasks; }

    public void setUserTasks(List<UserTask> userTasks) { this.userTasks = userTasks; }

    public List<UserSubject> getUserSubjects() { return userSubjects; }

    public void setUserSubjects(List<UserSubject> userSubjects) { this.userSubjects = userSubjects; }


}
