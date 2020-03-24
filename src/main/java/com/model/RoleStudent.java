package com.model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role_student")
public class RoleStudent extends RoleClass {

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "GROUP_ID")
    private Group group;

    @ManyToMany
    @JoinTable(name = "families")
    private List<RoleResponsible> responsibles;


    public RoleStudent() {
    }
}
