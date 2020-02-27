package com.model;

import com.model.enums.ProcedureStatus;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "procedures")
public class Procedure {
    private static final long serialVersionUID = 2681531852204068105L;
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "PROCEDURE_ID")
    private Integer procedureId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "USER_ID")
    private User userId;

    //@ManyToOne
    //@JoinColumn(name = "cartId")
    //private Cart cart;

    @Column(name = "TITLE")
    @NotNull
    private String title;

    @Column(name = "STATUS")
    @NotNull
    private ProcedureStatus status;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "ONLINE")
    @NotNull
    private boolean online;

    @Column(name = "CREATION_DATE")
    @NotNull
    private Date creationDate; //Java.util o Java.sql ??????

    @Column(name = "LIMIT_DATE")
    @NotNull
    private Date limitDate;

    public Procedure(String title, String description, boolean online, Date limitDate) {
        this.title = title;
        this.description = description;
        this.status = ProcedureStatus.CREATED;
        //this.creationDate
        //AGAFAR EL TIMESTAMP per la data de creacio
        this.online = online;
        this.limitDate = limitDate;
    }

    public Integer getProcedureId() {
        return procedureId;
    }

    public User getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ProcedureStatus getStatus() {
        return status;
    }

    public void setStatus(ProcedureStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(Date limitDate) {
        this.limitDate = limitDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Procedure procedure = (Procedure) o;
        return online == procedure.online &&
                Objects.equals(procedureId, procedure.procedureId) &&
                Objects.equals(userId, procedure.userId) &&
                Objects.equals(title, procedure.title) &&
                Objects.equals(description, procedure.description) &&
                Objects.equals(creationDate, procedure.creationDate) &&
                Objects.equals(limitDate, procedure.limitDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(procedureId, userId, title, description, online, creationDate, limitDate);
    }
}
