package com.dao;


import com.model.Space;
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

    public void deleteSpace(Space space){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.delete(space);
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

    public Space getById(String id){
        Session session = sessionFactory.openSession();
        Space space = (Space) session.getNamedQuery("Space.findById").setParameter("id", id).uniqueResult();
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
