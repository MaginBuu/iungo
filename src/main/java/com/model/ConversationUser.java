package com.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "ConversationUser.findByUserAndConversation", query = "SELECT c FROM ConversationUser c WHERE c.user = :user AND c.userConversation = :conversation"),
        @NamedQuery(name = "ConversationUser.findByConversation", query = "SELECT c FROM ConversationUser c WHERE c.userConversation.conversationId =:conversationId"),
        @NamedQuery(name = "ConversationUser.findByUser", query = "SELECT c FROM ConversationUser c WHERE c.user = :user"),
        @NamedQuery(name = "ConversationUser.findUnread", query = "SELECT c.unread FROM ConversationUser c WHERE c.user.userId = :userId AND c.userConversation.conversationId =:conversationId"),

})
public class ConversationUser implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn
    private User user;

    @Id
    @ManyToOne
    @JoinColumn
    private Conversation userConversation;

    @Column(name = "LAST_VISIT")
    private Date lastVisit;

    @Column(name = "NEW_MESSAGES")
    private boolean unread;

    public ConversationUser(){}

    public ConversationUser(User user, Conversation userConversation, Date lastVisit) {
        this.user = user;
        this.userConversation = userConversation;
        this.lastVisit = lastVisit;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Conversation getConversation() {
        return userConversation;
    }

    public void setConversation(Conversation userConversation) {
        this.userConversation = userConversation;
    }

    public Date getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(Date lastVisit) {
        this.lastVisit = lastVisit;
    }

    public boolean isUnread() { return unread; }

    public void newMessage() { this.unread = true; }

    public void messagesReaded() {this.unread = false; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConversationUser that = (ConversationUser) o;
        return Objects.equals(user, that.user) &&
                Objects.equals(userConversation, that.userConversation) &&
                Objects.equals(lastVisit, that.lastVisit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, userConversation, lastVisit);
    }
}