package com.model;


import com.model.enums.KeyRoleValue;

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
    KeyRoleValue keyRole;

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public KeyRoleValue getKeyRole() { return keyRole; }

    public void setKeyRole(KeyRoleValue keyRole) { this.keyRole = keyRole; }

    public KeyRole() { }
}
