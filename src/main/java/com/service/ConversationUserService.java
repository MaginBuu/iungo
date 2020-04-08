package com.service;

import com.model.ConversationUser;

import java.util.List;

public interface ConversationUserService {

    void addConversationUser(ConversationUser conversation);

    ConversationUser findByUserAndConversation(String userId, String conversationId);

    List<ConversationUser> findByConversation(String conversationId);

    void deleteConversationUser(ConversationUser conversationUser);

    boolean findUnread(String userId, String conversationId);

}
