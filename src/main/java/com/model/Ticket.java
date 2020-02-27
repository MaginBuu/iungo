package com.model;

import com.model.enums.TicketStatus;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ticket")
public class Ticket {

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "TICKET_ID")
    private String ticketId;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    //@Column("ATTACHED_FILES")
    //@OneToMany
    //private List<String> filePaths;

    @Column(name = "CREATION_DATE")
    private Date creationDate;

    @Column(name = "STATUS")
    private TicketStatus status;

    @OneToOne()
    @JoinColumn(name = "USER_ID")
    private User users;

    public Ticket(String title, String description, User users) {
        this.title = title;
        this.description = description;
        this.users = users;
    }

    public String getTicketId() {
        return ticketId;
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
