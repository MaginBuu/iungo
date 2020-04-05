package com.service;

import com.model.Conversation;
import com.model.Message;

import java.util.List;

public interface ConversationService {

    void addConversation(Conversation conversation);

    List<Conversation> getAllConversations();

    Conversation getWithMessages(String id);

}
