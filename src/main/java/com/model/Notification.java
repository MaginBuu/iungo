package com.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "notifications")
@NamedQueries({
        @NamedQuery(name = "Notifications.deleteFromUser", query = "DELETE FROM Notification WHERE user.userId =:id"),
})
public class Notification implements Comparable<Notification> {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "NOTIFICATION_ID")
    private String notificationId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name="PENDING")
    private boolean pending;

    @Column(name = "DATE")
    @NotNull
    private Date creationDate;

    public Notification() { }

    public String getNotificationId() { return notificationId; }

    public void setNotificationId(String notificationId) { this.notificationId = notificationId; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public boolean isPending() { return pending; }

    public void setPending(boolean pending) { this.pending = pending; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public int compareTo(Notification o) {
        return o.getCreationDate().compareTo(this.creationDate);
    }
}
