package com.dao;

import com.model.Subject;

import java.util.List;

public interface SubjectDao {

    void addSubject(Subject subject);

    List<Subject> getQueryResults(String query);
}
