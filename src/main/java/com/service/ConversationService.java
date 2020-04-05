package com.service;

import com.model.Conversation;
import java.util.List;

public interface ConversationService {

    void addConversation(Conversation conversation);

    List<Conversation> getAllConversations();

}
