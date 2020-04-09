package com.model;

import com.model.enums.Role;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "role_user")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class RoleClass {

    private static final long serialVersionUID = 2681531852204068105L;
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "ROLE_ID")
    private String roleId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "USER_ID")
    private User userR;

    @Column(name = "ROLE_KEY")
    private Role roleKey;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public User getUserR() {
        return userR;
    }

    public void setUserR(User userR) {
        this.userR = userR;
    }

    public Role getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(Role roleKey) {
        this.roleKey = roleKey;
    }

    @Override
    public String toString() {
        return "RoleClass{" +
                "roleId='" + roleId + '\'' +
                ", roleKey=" + roleKey +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        RoleClass roleClass = (RoleClass) o;
        return roleId.equals(roleClass.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId);
    }
}
