package com.dao;

import com.model.ConversationUser;

public interface ConversationUserDao {

    void addConversationUser(ConversationUser conversationUser);

    ConversationUser findByUserAndConversation(String userId, String conversationId);

    void deleteConversationUser(ConversationUser conversationUser);

}
