package com.dao;


import com.model.Space;
import com.model.TimeLine;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class SpaceDaoImpl implements SpaceDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void addSpace(Space space){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.saveOrUpdate(space);
            tx.commit();
        }catch(Exception e){
            if(tx != null) tx.rollback();
            throw e;
        }finally {
            session.close();
        }
    }

    public List<Space> getQueryResults(String query){
        Session session = sessionFactory.openSession();
        List<Space> spaces = session.createQuery(query).list();
        session.close();
        return spaces;
    }


    public Space getByIdWithTimeline(String id) {
        Session session = sessionFactory.openSession();
        Space space = (Space) session.getNamedQuery("Space.findByIdWithTimeline").setParameter("id", id).uniqueResult();
        session.close();
        return space;
    }

    public Space getByIdWithTimelineDay(String id, int day){
        Session session = sessionFactory.openSession();
        Space space = (Space) session.getNamedQuery("Space.findByIdWithTimelineDay").setParameter("id", id).setParameter("wd", day).uniqueResult();
        System.out.println("----------------");
        System.out.println("id: "+id+" - dia: "+day);
        for(TimeLine t : space.getTimelines()){
            System.out.println(t.getSpaceName());
        }
        session.close();
        return space;
    }

    public List<Space> getAll(){
        Session session = sessionFactory.openSession();
        List<Space> spaces = session.getNamedQuery("Space.findAll").list();
        session.close();
        return spaces;
    }
}
