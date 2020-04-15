package com.service;

import com.model.RoleTeacher;
import com.model.Subject;

import java.util.List;
import java.util.Set;

public interface SubjectService {

    void addSubject(Subject subject);

    void deleteSubject(Subject subject);

    List<Subject> getQueryResults(String query);

    Subject getByIdWithAll(String id);

    Subject getById(String id);

    Set<RoleTeacher> getTeachersBySubjectId(String id);

    List<Subject> getByGroup(String groupId);
}
