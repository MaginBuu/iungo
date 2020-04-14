package com.dao;

import com.model.Conversation;
import com.model.Message;

import java.util.List;

public interface ConversationDao {

    void addConversation(Conversation conversation);

    List<Conversation> getAllConversations();

    Conversation getWithMessages(String id);

    Conversation getWithUsers(String id);

    Conversation findById(String id);

    Conversation findBy2Id(String id1, String id2);

    List<Conversation> findAllConversationsByUserId(String userId);
}
