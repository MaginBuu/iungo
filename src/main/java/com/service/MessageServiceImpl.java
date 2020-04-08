package com.service;

import com.dao.MessageDao;
import com.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageDao messageDao;

    public void addMessage(Message message){
        messageDao.addMessage(message);
    }

}
