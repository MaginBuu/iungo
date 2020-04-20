package com.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "resources")
public class Resource {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "RESOURCE_ID")
    private String resourceId;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "FILE")
    private String path;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "CHAPTER_ID")
    private Chapter chapter;


    public Resource() { }

    public String getResourceId() { return resourceId; }

    public void setResourceId(String resourceId) { this.resourceId = resourceId; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getPath() { return path; }

    public void setPath(String path) { this.path = path; }

    public Chapter getChapter() { return chapter; }

    public void setChapter(Chapter chapter) { this.chapter = chapter; }
}
