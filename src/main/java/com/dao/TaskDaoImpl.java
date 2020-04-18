package com.dao;

import com.model.Task;
import com.model.Ticket;
import com.model.UserTask;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class TaskDaoImpl implements TaskDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void addTask(Task task){
        System.out.println("ticketCreation");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.saveOrUpdate(task);
            tx.commit();
        }catch(Exception e){
            if(tx != null) tx.rollback();
            throw e;
        }finally {
            session.close();
        }
    }

    public List<UserTask> getUserTaskByUserAndSubject(String userId, String subjectId) {
        Session session = sessionFactory.openSession();
        List<UserTask> userTasks = session.getNamedQuery("UserTask.findByUserAndSubject").setParameter("subjectId", subjectId).setParameter("userId", userId).list();
        session.close();
        return userTasks;
    }

    public void addUserTask(UserTask userTask){
        System.out.println("ticketUpdate");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.saveOrUpdate(userTask);
            tx.commit();
        }catch(Exception e){
            if(tx != null) tx.rollback();
            throw e;
        }finally {
            session.close();
        }
    }

}
