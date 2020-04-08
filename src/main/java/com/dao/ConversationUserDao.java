package com.dao;

import com.model.ConversationUser;

import java.util.List;

public interface ConversationUserDao {

    void addConversationUser(ConversationUser conversationUser);

    ConversationUser findByUserAndConversation(String userId, String conversationId);

    List<ConversationUser> findByConversation(String conversationId);

    void deleteConversationUser(ConversationUser conversationUser);

    boolean findUnread(String userId, String conversationId);

}
