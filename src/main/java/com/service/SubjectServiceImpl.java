package com.service;

import com.dao.SubjectDao;
import com.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    SubjectDao subjectDao;

    public void addSubject(Subject subject){
        subjectDao.addSubject(subject);
    }

    public List<Subject> getQueryResults(String query) { return subjectDao.getQueryResults(query); }
}
