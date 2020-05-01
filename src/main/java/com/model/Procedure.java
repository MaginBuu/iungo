package com.model;

import com.model.enums.ProcedureStatus;
import com.service.UserService;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "procedures")
@NamedQueries({
//     @NamedQuery(name = "Room.findById", query = "SELECT r,te.email FROM Room r  "
//             + "LEFT JOIN Tenant te ON te.room = r.id"
//             + "WHERE r.id = :id")
        @NamedQuery(name = "Procedure.findById", query = "SELECT r FROM Procedure r WHERE r.procedureId = :id"),

})
public class Procedure implements Comparable {
    private static final long serialVersionUID = 2681531852204068105L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "PROCEDURE_ID")
    private String procedureId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "USER_ID")
    private User userP;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "CREATOR_ID")
    private User creator;


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

    @Column(name = "NOTIFY")
    @NotNull
    private boolean notify;

    @Column(name = "CREATION_DATE")
    @NotNull
    private Date creationDate; //Java.util o Java.sql ??????

    @Column(name = "LIMIT_DATE")
    @NotNull
    private Date limitDate;

    @Transient
    private String hour;

    public Procedure(){}

    public Procedure(String title, String description, boolean online, Date limitDate) {
        this.title = title;
        this.description = description;
        this.status = ProcedureStatus.CREATED;
        //this.creationDate
        //AGAFAR EL TIMESTAMP per la data de creacio
        this.online = online;
        this.limitDate = limitDate;
        this.creationDate = new Date();
    }

    public String getProcedureId() { return procedureId; }

    public User getUserP() { return userP; }

    public void setUserP(User user) { this.userP = user;}

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public ProcedureStatus getStatus() { return status; }

    public void setStatus(ProcedureStatus status) { this.status = status; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public boolean isOnline() { return online; }

    public void setOnline(boolean online) { this.online = online; }

    public Date getCreationDate() { return creationDate; }

    public void setCreationDate(Date creationDate) { this.creationDate = creationDate; }

    public Date getLimitDate() { return limitDate; }

    public void setLimitDate(Date limitDate) { this.limitDate = limitDate; }

    public String getHour() { return hour; }

    public void setHour(String hour) { this.hour = hour; }

    public User getCreator() { return creator; }

    public void setCreator(User creator) { this.creator = creator; }

    public boolean isNotify() { return notify; }

    public void setNotify(boolean notify) { this.notify = notify; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Procedure procedure = (Procedure) o;
        return online == procedure.online &&
                Objects.equals(procedureId, procedure.procedureId) &&
                Objects.equals(userP, procedure.userP) &&
                Objects.equals(title, procedure.title) &&
                Objects.equals(description, procedure.description) &&
                Objects.equals(creationDate, procedure.creationDate) &&
                Objects.equals(limitDate, procedure.limitDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(procedureId, userP, title, description, online, creationDate, limitDate);
    }

    public Procedure clone(){
        return new Procedure(this.title, this.description, this.online, this.limitDate);
    }

    @Override
    public int compareTo(Object o) {
        return this.creationDate.compareTo(((Procedure)o).creationDate) * -1;
    }
}
