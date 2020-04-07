package com.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "conversation")
@NamedQueries({
        @NamedQuery(name = "Conversation.findAll", query = "SELECT c FROM Conversation c"),
        @NamedQuery(name = "Conversation.getWithMessages", query = "SELECT c FROM Conversation c LEFT JOIN FETCH c.userConversations u WHERE c.conversationId =:id"),
        @NamedQuery(name = "Conversation.findById", query = "SELECT c FROM Conversation c WHERE c.conversationId =:id"),
})
public class Conversation {
    private static final long serialVersionUID = 2681531852204068105L;

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "CONVERSATION_ID")
    private String conversationId;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION", length = 100)
    private String description;

    @Column(name = "REPORTED", columnDefinition = "boolean default false")
    private boolean reported;

    @OneToMany(mappedBy = "conversation", targetEntity = Message.class)
    private List<Message> messages;

    @OneToMany(mappedBy = "userConversation", targetEntity = ConversationUser.class)
    private List<ConversationUser> userConversations = new LinkedList<>();

    @Transient
    private String usersTemp;

    @Transient
    private Date lastVisit;

    /*
    @ManyToMany(cascade = CascadeType.PERSIST, fetch=FetchType.LAZY)
    @JoinTable(name = "user_conversations")
    private List<User> users = new LinkedList<>();
*/

    public Conversation() {}

    public void setConversationId(String conversationId) { this.conversationId = conversationId; }

    public String getConversationId(){
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

    public String getUsersTemp() { return usersTemp; }

    public void setUsersTemp(String usersTemp) { this.usersTemp = usersTemp; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    /*public void addUser(User user){ this.users.add(user); }

    public List<User> getUsersConversation() { return users; }

    public void setUsersConversation(List<User> usersConversation) { this.users = usersConversation; }*/

    public void addUserConversations(ConversationUser conversationUser){
        this.userConversations.add(conversationUser);
    }

    public List<ConversationUser> getUserConversations() {
        return userConversations;
    }

    public void setUserConversations(List<ConversationUser> userConversations) {
        this.userConversations = userConversations;
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
