package com.service;

import com.dao.SubjectDao;
import com.model.ClassGroup;
import com.model.RoleTeacher;
import com.model.Subject;
import com.model.UserSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    SubjectDao subjectDao;

    public void addSubject(Subject subject){
        subjectDao.addSubject(subject);
    }

    public void deleteSubject(Subject subject){subjectDao.deleteSubject(subject);}

    public List<Subject> getQueryResults(String query) { return subjectDao.getQueryResults(query); }

    public Subject getByIdWithAll(String id) { return subjectDao.getByIdWithAll(id); }

    public Subject getByIdWithChapters(String id) { return subjectDao.getByIdWithChapters(id); }

    public Subject getById(String id) { return subjectDao.getById(id); }

    public Set<RoleTeacher> getTeachersBySubjectId(String id) { return subjectDao.getTeachersBySubjectId(id); }

    public List<Subject> getByGroup(String groupId){ return subjectDao.getByGroup(groupId);}

    public UserSubject getUserSubjectByUserAndSubject(String userId, String subjectId) { return subjectDao.getUserSubjectByUserAndSubject(userId, subjectId); }

    public List<Subject> getByGroupNoTeachers(String groupId) { return subjectDao.getByGroupNoTeachers(groupId); }


}
