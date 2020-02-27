package com.model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ticket")
public class Reminder {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "REMINDER_ID")
    private String reminderId;


    @Column(name = "DATE")
    private Date date;


    @Column(name = "TITLE")
    private String title;
}
