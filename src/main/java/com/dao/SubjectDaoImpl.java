package com.dao;

import com.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

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

    public void deleteSubject(Subject subject){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.delete(subject);
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

    public Subject getByIdWithChapters(String id){
        Session session = sessionFactory.openSession();
        Subject subject = (Subject) session.getNamedQuery("Subject.findByIdWithChapters").setParameter("id", id).uniqueResult();
        session.close();
        return subject;
    }

    public Subject getByIdWithAll(String id){
        Session session = sessionFactory.openSession();
        Subject subject = (Subject) session.getNamedQuery("Subject.findByIdWithAll").setParameter("id", id).uniqueResult();
        session.close();
        return subject;
    }

    public Subject getById(String id){
        Session session = sessionFactory.openSession();
        Subject subject = (Subject) session.getNamedQuery("Subject.findById").setParameter("id", id).uniqueResult();
        session.close();
        return subject;
    }

    public Set<RoleTeacher> getTeachersBySubjectId(String id){
        Session session = sessionFactory.openSession();
        Subject subject = (Subject) session.getNamedQuery("Subject.findTeachersBySubjectId").setParameter("id", id).uniqueResult();
        session.close();
        return subject.getTeachers();
    }

    public List<Subject> getByGroup(String groupId){
        Session session = sessionFactory.openSession();
        List<Subject> subject = session.getNamedQuery("Subject.findByGroupId").setParameter("id", groupId).list();
        session.close();
        return subject;
    }

    public List<Subject> getByGroupNoTeachers(String groupId){
        Session session = sessionFactory.openSession();
        List<Subject> subject = session.getNamedQuery("Subject.findByGroupIdNoTeachers").setParameter("id", groupId).list();
        session.close();
        return subject;
    }

    public UserSubject getUserSubjectByUserAndSubject(String userId, String subjectId){
        Session session = sessionFactory.openSession();
        UserSubject userSubject = (UserSubject) session.getNamedQuery("UserSubject.findByUserAndSubject").setParameter("subjectId", subjectId).setParameter("userId", userId).uniqueResult();
        session.close();
        return userSubject;
    }

    public UserSubject getUserSubjectByUserAndSubjectAndEvaluation(String userId, String subjectId, String evaluationId){
        Session session = sessionFactory.openSession();
        UserSubject userSubject = (UserSubject) session.getNamedQuery("UserSubject.findByUserAndSubjectAndEvaluation").setParameter("evaluationId", evaluationId).setParameter("subjectId", subjectId).setParameter("userId", userId).uniqueResult();
        session.close();
        return userSubject;
    }

}
