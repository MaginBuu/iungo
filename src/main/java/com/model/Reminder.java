package com.model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "reminders")
public class Reminder {

    private static final long serialVersionUID = 2681531852204068105L;
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "REMINDER_ID")
    private String reminderId;


    @Column(name = "DATE")
    private Date date;


    @Column(name = "TITLE")
    private String title;

    public Reminder(String title) {
        this.title = title;
        //this.creationDate = new Date();
    }

    public Reminder() {

    }

    public String getReminderId() {
        return reminderId;
    }

    public Date getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reminder reminder = (Reminder) o;
        return reminderId.equals(reminder.reminderId) &&
                date.equals(reminder.date) &&
                title.equals(reminder.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reminderId, date, title);
    }
}
