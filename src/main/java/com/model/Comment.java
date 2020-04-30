package com.model;

import com.model.enums.FaultType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "comments")

@NamedQueries({
        @NamedQuery(name = "Comment.findById", query = "SELECT i FROM Comment i WHERE i.commentId =:id"),
})
public class Comment {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "COMMENT_ID")
    private String commentId;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @OneToOne
    private User user;

    @Column(name = "CREATION_DATE")
    private Date creationDate;


    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public String getCommentId() { return commentId; }

    public void setCommentId(String commentId) { this.commentId = commentId; }

    public Date getCreationDate() { return creationDate; }

    public void setCreationDate(Date creationDate) { this.creationDate = creationDate; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Comment() { }


}
