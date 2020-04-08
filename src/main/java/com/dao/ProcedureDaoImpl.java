package com.dao;

import com.model.Procedure;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ProcedureDaoImpl implements ProcedureDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void addProcedure(Procedure procedure){
        System.out.println("procedureCreation");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.saveOrUpdate(procedure);
            tx.commit();
        }catch(Exception e){
            if(tx != null) tx.rollback();
            throw e;
        }finally {
            session.close();
        }
    }

    @Override
    public Procedure findById(String id) {
        Session session = sessionFactory.openSession();
        Procedure procedure = (Procedure) session.getNamedQuery("Procedure.findById").setParameter("id", id).uniqueResult();
        session.close();
        return procedure;
    }
}
