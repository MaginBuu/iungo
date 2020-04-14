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

    @Override
    public Conversation getWithUsers(String id){ return conversationDao.getWithUsers(id);}

    @Override
    public Conversation findById(String id) { return conversationDao.findById(id); }

    @Override
    public Conversation findBy2Id(String id1, String id2) { return conversationDao.findBy2Id(id1, id2); }

    @Override
    public List<Conversation> findAllConversationsByUserId(String userId) { return conversationDao.findAllConversationsByUserId(userId); }

}
