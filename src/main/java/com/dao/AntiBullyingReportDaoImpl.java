package com.dao;

import com.model.AntiBullyingReport;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class AntiBullyingReportDaoImpl implements AntiBullyingReportDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void addAntiBullyingReport(AntiBullyingReport report){
        System.out.println("reportCreation");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.saveOrUpdate(report);
            tx.commit();
        }catch(Exception e){
            if(tx != null) tx.rollback();
            throw e;
        }finally {
            session.close();
        }
    }

    public void deleteAntiBullyingReport(AntiBullyingReport timeLine){
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


    public AntiBullyingReport getById(String id) {
        Session session = sessionFactory.openSession();
        AntiBullyingReport report = (AntiBullyingReport) session.getNamedQuery("AntiBullyingReport.findById").setParameter("id", id).uniqueResult();
        session.close();
        return report;
    }
}
