package com.model;

import com.model.enums.TicketStatus;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

import static com.model.enums.TicketStatus.CREATED;

@Entity
@Table(name = "tickets")
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

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "USER_ID")
    private User userId;

    public Ticket(){}

    public Ticket(String title, String description, User users) {
        this.title = title;
        this.description = description;
        this.userId = users;
        //this.creationDate = new Date();
        this.status = CREATED;
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

    public User getUserId() {
        return userId;
    }

    public void setUserId(User users) {
        this.userId = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return ticketId.equals(ticket.ticketId) &&
                title.equals(ticket.title) &&
                Objects.equals(description, ticket.description) &&
                creationDate.equals(ticket.creationDate) &&
                users.equals(ticket.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId, title, description, creationDate, users);
    }
}
