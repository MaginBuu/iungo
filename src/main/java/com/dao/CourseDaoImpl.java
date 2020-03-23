package com.dao;

import com.model.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class CourseDaoImpl implements CourseDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void addCourse(Course course){
        System.out.println("courseCreation");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.save(course);
            tx.commit();
        }catch(Exception e){
            if(tx != null) tx.rollback();
            throw e;
        }finally {
            session.close();
        }
    }

    @Override
    public List<Course> getAllCourses() {
        Session session = sessionFactory.openSession();
        List<Course> courses =  session.getNamedQuery("Courses.findAll").list();
        session.close();
        return courses;
    }

    @Override
    public Course findByDate(int date) {
        Session session = sessionFactory.openSession();
        System.out.println(date + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        List<Course> courses =  session.getNamedQuery("Courses.findByStart").setParameter("startDate", date).list();
        session.close();
        return courses.get(0);
    }
}
