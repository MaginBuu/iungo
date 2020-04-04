package com.dao;

import com.model.Conversation;

import java.util.List;

public interface ConversationDao {

    void addConversation(Conversation conversation);

    List<Conversation> getAllConversations();
}
