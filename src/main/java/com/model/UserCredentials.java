package com.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class UserCredentials implements Serializable {

    @Id
    @OneToOne
    @JoinColumn(name = "USER", referencedColumnName = "USER_ID")
    //@NotNull
    private User user;

    @Column(name = "PASSWORD")
    //@NotNull
    private String password;
}
