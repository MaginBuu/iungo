package com.dao;

import com.model.Subject;

import java.util.List;

public interface SubjectDao {

    void addSubject(Subject subject);

    void deleteSubject(Subject subject);

    List<Subject> getQueryResults(String query);

    Subject getByIdWithAll(String id);

    Subject getById(String id);
}
