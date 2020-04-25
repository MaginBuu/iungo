package com.dao;

import com.model.ClassGroup;
import com.model.Subject;
import com.model.enums.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


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

    public List<Integer> getLevelsByStage(Stage stage) {
        Session session = sessionFactory.openSession();
        Set<Integer> levelsSet = new HashSet<>(session.getNamedQuery("ClassGroup.findLevelsByStage").setParameter("stage", stage).list());
        List<Integer> levels = new LinkedList<>(levelsSet);
        session.close();
        return levels;
    }

    public List<String> getGroupsByStageAndLevel(Stage stage, int level) {
        Session session = sessionFactory.openSession();
        List<String> groups = session.getNamedQuery("ClassGroup.findGroupsByStageAndLevel").setParameter("stage", stage).setParameter("level", level).list();
        session.close();
        return groups;
    }

    public ClassGroup getGroupsByStageAndLevelAndGroupAndCourse(ClassGroup groupTmp) {
        Session session = sessionFactory.openSession();
        ClassGroup group = (ClassGroup) session.getNamedQuery("ClassGroup.findGroupByStageAndLevelAndGroupAndCourse").setParameter("stage", groupTmp.getStage()).setParameter("level", groupTmp.getLevel())
                            .setParameter("group", groupTmp.getGroup()).setParameter("course", groupTmp.getCourse()).uniqueResult();
        session.close();
        return group;
    }


}
