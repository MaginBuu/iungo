package com.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "NOTIFICATION_ID")
    private String notificationId;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name="PENDING")
    private boolean pending;

    public Notification() { }

    public String getNotificationId() { return notificationId; }

    public void setNotificationId(String notificationId) { this.notificationId = notificationId; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public boolean isPending() { return pending; }

    public void setPending(boolean pending) { this.pending = pending; }
}
