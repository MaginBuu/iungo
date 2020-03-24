package com.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role_responsible")
public class RoleResponsible extends RoleClass {

    @ManyToMany(mappedBy="responsibles")
    private List<RoleStudent> child;

}
