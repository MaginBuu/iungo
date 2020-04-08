package com.service;

import com.dao.ConversationUserDao;
import com.model.ConversationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ConversationUserServiceImpl implements ConversationUserService{

    @Autowired
    ConversationUserDao conversationUserDao;

    @Override
    public void addConversationUser(ConversationUser conversationUser){ conversationUserDao.addConversationUser(conversationUser); }

    public ConversationUser findByUserAndConversation(String userId, String conversationId) { return conversationUserDao.findByUserAndConversation(userId, conversationId); }

    public List<ConversationUser> findByConversation(String conversationId) { return conversationUserDao.findByConversation(conversationId); }

    public void deleteConversationUser(ConversationUser conversationUser){ conversationUserDao.deleteConversationUser(conversationUser); }

    public boolean findUnread(String userId, String conversationId){ return conversationUserDao.findUnread(userId, conversationId); }


}
