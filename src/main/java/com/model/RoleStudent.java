package com.model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "role_student")
public class RoleStudent extends RoleClass {

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "GROUP_ID")
    private ClassGroup group;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "families")
    private List<RoleResponsible> responsibles = new LinkedList<>();

    @OneToMany(mappedBy = "student")
    private List<UserTask> userTasks;

    public RoleStudent() { }

    public ClassGroup getGroup() { return group; }

    public void setGroup(ClassGroup group) { this.group = group; }

    public List<RoleResponsible> getResponsibles() { return responsibles; }

    public void setResponsibles(List<RoleResponsible> responsibles) { this.responsibles = responsibles; }

    public void addResponsible(RoleResponsible responsible){ this.responsibles.add(responsible); }
}
