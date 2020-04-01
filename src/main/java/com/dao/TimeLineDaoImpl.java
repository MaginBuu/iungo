package com.dao;

import com.model.TimeLine;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class TimeLineDaoImpl implements TimeLineDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void addTimeLine(TimeLine timeline){
        System.out.println("timelineCreation");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.saveOrUpdate(timeline);
            tx.commit();
        }catch(Exception e){
            if(tx != null) tx.rollback();
            throw e;
        }finally {
            session.close();
        }
    }

    public void deleteTimeLine(TimeLine timeLine){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.delete(timeLine);
            tx.commit();
        }catch(Exception e){
            if(tx != null) tx.rollback();
            throw e;
        }finally {
            session.close();
        }
    }


    public TimeLine getById(String id) {
        Session session = sessionFactory.openSession();
        TimeLine timeline = (TimeLine) session.getNamedQuery("TimeLine.findById").setParameter("id", id).uniqueResult();
        session.close();
        return timeline;
    }
}
