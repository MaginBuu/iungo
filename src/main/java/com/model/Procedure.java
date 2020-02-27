package com.model;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "procedures")
public class Procedure {
    private static final long serialVersionUID = 2681531852204068105L;
    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "PROCEDURE_ID")
    private Integer procedureId;



    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "USER_ID")
    private User userId;

    //@ManyToOne
    //@JoinColumn(name = "cartId")
    //private Cart cart;
}
