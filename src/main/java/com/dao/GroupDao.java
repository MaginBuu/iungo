package com.dao;

import com.model.ClassGroup;

import java.util.List;

public interface GroupDao {

    void addGroup(ClassGroup group);

    List<ClassGroup> getQueryResults(String query);

    ClassGroup getClassGroupById(String id);
}
