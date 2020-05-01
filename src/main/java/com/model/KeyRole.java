package com.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "key_role")

@NamedQueries({
        @NamedQuery(name = "KeyRole.getUserByKeyRole", query = "SELECT k.user FROM KeyRole k WHERE k.keyRole =:keyRole"),

})
public class KeyRole implements Serializable {

    @Id
    @OneToOne
    User user;

    @Id
    @Column(name = "KEY_ROLE")
    String keyRole;

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public String getKeyRole() { return keyRole; }

    public void setKeyRole(String keyRole) { this.keyRole = keyRole; }

    public KeyRole() { }
}
