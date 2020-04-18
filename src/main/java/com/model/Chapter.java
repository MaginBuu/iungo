package com.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "chapters")
public class Chapter {

    private static final long serialVersionUID = 2681531852204068105L;
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "CHAPTER_ID")
    private String chapterId;

    @Column(name = "TITLE")
    private String title;

    @OneToMany(mappedBy="chapter", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Task> tasks;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "SUBJECT_ID")
    private Subject subject;

    @OneToOne
    private Chapter nextChapter;

    public Chapter() { }

    public String getChapterId() { return chapterId; }

    public void setChapterId(String chapterId) { this.chapterId = chapterId; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public List<Task> getTasks() { return tasks; }

    public void setTasks(List<Task> tasks) { this.tasks = tasks; }

    public Subject getSubject() { return subject; }

    public void setSubject(Subject subject) { this.subject = subject; }

}
