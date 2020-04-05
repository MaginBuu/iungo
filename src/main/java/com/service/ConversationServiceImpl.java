package com.service;

import com.dao.ConversationDao;
import com.model.Conversation;
import com.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ConversationServiceImpl implements ConversationService{

    @Autowired
    ConversationDao conversationDao;

    @Override
    public void addConversation(Conversation conversation){
        conversationDao.addConversation(conversation);
    }

    @Override
    public List<Conversation> getAllConversations() {
        return conversationDao.getAllConversations();
    }

    @Override
    public Conversation getWithMessages(String id) { return conversationDao.getWithMessages(id); }

}
