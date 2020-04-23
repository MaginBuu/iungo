package com.model;


import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "role_admin")
@NamedQueries({
        @NamedQuery(name = "RoleAdmin.getAll", query ="SELECT o.userR FROM RoleAdmin o"),
})
public class RoleAdmin extends RoleClass {


}
