package com.model;


import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "role_responsible")
public class RoleResponsible extends RoleClass {

    @ManyToMany(mappedBy="responsibles" , fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    private List<RoleStudent> child = new LinkedList<>();

    public RoleResponsible() { }

    public List<RoleStudent> getChild() { return child; }

    public void setChild(List<RoleStudent> child) { this.child = child; }

    public void addChild(RoleStudent child){ this.child.add(child); }
}
