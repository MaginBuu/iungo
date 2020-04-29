package com.dao;

import com.model.Incidence;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class IncidenceDaoImpl implements IncidenceDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void addIncidence(Incidence incidence){
        System.out.println("incidenceCreation");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.saveOrUpdate(incidence);
            tx.commit();
        }catch(Exception e){
            if(tx != null) tx.rollback();
            throw e;
        }finally {
            session.close();
        }
    }


    public Incidence getIncidenceById(String id){
        Session session = sessionFactory.openSession();
        Incidence incidences = (Incidence) session.getNamedQuery("Incidence.findById").setParameter("id", id).uniqueResult();
        session.close();
        return incidences;
    }

    public Incidence getIncidenceByProcedureId(String id){
        Session session = sessionFactory.openSession();
        Incidence incidences = (Incidence) session.getNamedQuery("Incidence.findByProcedureId").setParameter("id", id).uniqueResult();
        session.close();
        return incidences;
    }
}
