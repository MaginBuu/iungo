package com.dao;

import com.model.Conversation;
import com.model.Message;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ConversationDaoImpl implements ConversationDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void addConversation(Conversation conversation){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.saveOrUpdate(conversation);
            tx.commit();
        }catch(Exception e){
            if(tx != null) tx.rollback();
            throw e;
        }finally {
            session.close();
        }
    }

    @Override
    public List<Conversation> getAllConversations() {
        Session session = sessionFactory.openSession();
        List<Conversation> conversations =  session.getNamedQuery("Conversation.findAll").list();
        session.close();
        return conversations;
    }

    @Override
    public Conversation getWithMessages(String id) {
        Session session = sessionFactory.openSession();
        Conversation conversation =  (Conversation) session.getNamedQuery("Conversation.getWithMessages").setParameter("id", id).uniqueResult();
        List<Message> messages = session.getNamedQuery("Message.getByConversationId").setParameter("conversationId", id).list();
        conversation.setMessages(messages);
        session.close();
        return conversation;
    }
}
