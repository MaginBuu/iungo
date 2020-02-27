package com.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "messages")
public class Message {
    private static final long serialVersionUID = 2681531852204068105L;
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "MESSAGE_ID")
    private int messageId;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "CONVERSATION_ID")
    private Conversation conversationId;

    @Column(name = "SUBJECT")
    private String subject;

    @Column(name = "MESSAGE_BODY")
    @NotNull
    private String messageBody;

    @Column(name = "DATE")
    @NotNull
    private Date date;

    //FITXERS ADJUNTS

    public Message() {
    }

    public Message(Conversation conversationId, String subject, String messageBody) {
        this.conversationId = conversationId;
        this.subject = subject;
        this.messageBody = messageBody;
    }

    public int getMessageId() {
        return messageId;
    }

    public Conversation getConversationId() {
        return conversationId;
    }

    //public void setConversationId(Conversation conversationId) {
    //    this.conversationId = conversationId;
    //}

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return messageId == message.messageId &&
                Objects.equals(conversationId, message.conversationId) &&
                Objects.equals(subject, message.subject) &&
                Objects.equals(messageBody, message.messageBody) &&
                Objects.equals(date, message.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageId, conversationId, subject, messageBody, date);
    }
}
