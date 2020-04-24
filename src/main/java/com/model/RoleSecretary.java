package com.model;


import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "role_secretary")
@NamedQueries({
        @NamedQuery(name = "RoleSecretary.getAll", query ="SELECT o.userR FROM RoleSecretary o"),
})
public class RoleSecretary extends RoleClass {


}
