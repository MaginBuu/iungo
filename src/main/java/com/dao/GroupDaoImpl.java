package com.dao;

import com.model.ClassGroup;
import com.model.Subject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class GroupDaoImpl implements GroupDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void addGroup(ClassGroup group){
        System.out.println("groupCreation");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.save(group);
            tx.commit();
        }catch(Exception e){
            if(tx != null) tx.rollback();
            throw e;
        }finally {
            session.close();
        }
    }

    public List<ClassGroup> getQueryResults(String query){
        Session session = sessionFactory.openSession();
        List<ClassGroup> groups = session.createQuery(query).list();
        session.close();
        return groups;
    }

    public List<ClassGroup> getAllClassGroup(){
        Session session = sessionFactory.openSession();
        List<ClassGroup> groups = session.getNamedQuery("ClassGroup.findAll").list();
        session.close();
        return groups;
    }

   public ClassGroup getClassGroupById(String id){
       Session session = sessionFactory.openSession();
       ClassGroup group = (ClassGroup) session.getNamedQuery("ClassGroup.findById").setParameter("id", id).uniqueResult();
       session.close();
       return group;
    }

    public ClassGroup getGroupBySubjectId(String id) {
        Session session = sessionFactory.openSession();
        Subject subject = (Subject) session.getNamedQuery("Subject.findById").setParameter("id", id).uniqueResult();
        ClassGroup cg = (ClassGroup) session.getNamedQuery("ClassGroup.findByIdWithStudents").setParameter("id",subject.getSubjectGroup().getGroupId()).uniqueResult();
        session.close();
        return cg;
    }
}
