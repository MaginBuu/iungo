package com.model;


import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_credentials")
public class UserCredentials implements Serializable {

    @Id
    @Column(name = "PASSWORD")
    @NotNull
    private String password;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
