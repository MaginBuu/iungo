package com.dao;

import com.model.Space;
import com.model.Subject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubjectDaoImpl implements SubjectDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void addSubject(Subject subject){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.saveOrUpdate(subject);
            tx.commit();
        }catch(Exception e){
            if(tx != null) tx.rollback();
            throw e;
        }finally {
            session.close();
        }
    }

    public List<Subject> getQueryResults(String query){
        Session session = sessionFactory.openSession();
        List<Subject> subjects = session.createQuery(query).list();
        return subjects;
    }

    public Subject getByIdWithAll(String id){
        Session session = sessionFactory.openSession();
        Subject subject = (Subject) session.getNamedQuery("Subject.findByIdWithAll").setParameter("id", id).uniqueResult();
        session.close();
        return subject;
    }
}
