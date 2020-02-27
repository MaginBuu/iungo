package com.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ticket")
public class Ticket {

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column("TICKET_ID")
    private String ticketID;

    @Column("TITLE")
    private String title;

    @Column("DESCRIPTION")
    private String description;

    //@Column("ATTACHED_FILES")
    //@OneToMany
    //private List<String> filePaths;

    @Column("CREATION_DATE")
    private Date creationDate;

    @Column("STATUS")
    private TicketStatus status;

    @OneToOne()
    @JoinColumn(name = "USER_ID")
    private User users;

    public String getTicketID() {
        return ticketID;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }
}
