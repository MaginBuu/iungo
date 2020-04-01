package com.service;

import com.model.ClassGroup;

import java.util.List;

public interface GroupService {

    void addGroup(ClassGroup group);

    List<ClassGroup> getQueryResults(String query);

    List<ClassGroup> getAllClassGroup();

    ClassGroup getClassGroupById(String id);
}
