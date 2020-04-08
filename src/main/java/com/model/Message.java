package com.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "messages")

@NamedQueries({
        @NamedQuery(name = "Message.getByConversationId", query = "SELECT m FROM Message m WHERE m.conversation.conversationId =:conversationId ORDER BY m.date DESC"),
})

public class Message {
    private static final long serialVersionUID = 2681531852204068105L;
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "MESSAGE_ID")
    private String messageId;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "CONVERSATION_ID")
    private Conversation conversation;

    @Column(name = "SUBJECT")
    private String subject;

    @Column(name = "MESSAGE_BODY")
    @Type(type="text")
    private String messageBody;

    @Column(name = "DATE")
    @NotNull
    private Date date;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "USER_ID")
    private User sender;


    //FITXERS ADJUNTS

    public Message() {
    }

    public Message(Conversation conversation, String subject, String messageBody) {
        this.conversation = conversation;
        this.subject = subject;
        this.messageBody = messageBody;
    }

    public void setMessageId(String messageId) { this.messageId = messageId; }

    public String getMessageId() {
        return messageId;
    }

    public Conversation getConversationId() {
        return conversation;
    }

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

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Conversation getConversation() { return conversation; }

    public void setConversation(Conversation conversation) { this.conversation = conversation; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return messageId == message.messageId &&
                Objects.equals(conversation, message.conversation) &&
                Objects.equals(subject, message.subject) &&
                Objects.equals(messageBody, message.messageBody) &&
                Objects.equals(date, message.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageId, conversation, subject, messageBody, date);
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", conversation=" + conversation +
                ", subject='" + subject + '\'' +
                ", messageBody='" + messageBody + '\'' +
                ", date=" + date +
                ", sender=" + sender +
                '}';
    }
}
