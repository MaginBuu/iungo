package com.service;

import com.model.Subject;

import java.util.List;

public interface SubjectService {

    void addSubject(Subject subject);

    List<Subject> getQueryResults(String query);

    Subject getByIdWithAll(String id);
}
