package com.dao;

import com.model.Conversation;
import com.model.ConversationUser;
import com.model.Message;
import com.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ConversationUserDaoImpl implements ConversationUserDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void addConversationUser(ConversationUser conversationUser){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.saveOrUpdate(conversationUser);
            tx.commit();
        }catch(Exception e){
            if(tx != null) tx.rollback();
            throw e;
        }finally {
            session.close();
        }
    }

    public ConversationUser findByUserAndConversation(String userId, String conversationId){
        Session session = sessionFactory.openSession();
        User user = new User();
        user.setUserId(userId);
        Conversation conversation = new Conversation();
        conversation.setConversationId(conversationId);
        ConversationUser conversationUser = (ConversationUser) session.getNamedQuery("ConversationUser.findByUserAndConversation").setParameter("user", user)
                .setParameter("conversation", conversation).uniqueResult();
        session.close();
        return conversationUser;
    }

    public void deleteConversationUser(ConversationUser conversationUser){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.delete(conversationUser);
            tx.commit();
        }catch(Exception e){
            if(tx != null) tx.rollback();
            throw e;
        }finally {
            session.close();
        }
    }

}
