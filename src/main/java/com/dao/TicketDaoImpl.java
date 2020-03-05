package com.dao;

import com.model.Authorities;
import com.model.Ticket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

public class TicketDaoImpl {

    @Autowired
    private SessionFactory sessionFactory;

    public void addTicket(Ticket ticket){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Authorities authorities = new Authorities();
        try{
            tx = session.beginTransaction();
            session.save(ticket);
            tx.commit();
        }catch(Exception e){
            if(tx != null) tx.rollback();
            throw e;
        }finally {
            session.close();
        }

    }
}
