package com.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "conversation")
public class Conversation {
    private static final long serialVersionUID = 2681531852204068105L;
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "CONVERSATION_ID")
    private int conversationId;

    @Column(name = "REPORTED", columnDefinition = "boolean default false")
    private boolean reported;

    @OneToMany(targetEntity = Message.class)
    private List<Message> messages;

    public Conversation() {
    }

    public int getConversationId(){
        return conversationId;
    }

    public boolean isReported() {
        return reported;
    }

    public void setReported(boolean reported) {
        this.reported = reported;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conversation that = (Conversation) o;
        return conversationId == that.conversationId &&
                reported == that.reported;
    }

    @Override
    public int hashCode() {
        return Objects.hash(conversationId, reported);
    }
}
