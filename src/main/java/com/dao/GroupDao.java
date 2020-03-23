package com.dao;

import com.model.Group;

import java.util.List;

public interface GroupDao {

    void addGroup(Group group);

    List<Group> getQueryResults(String query);
}
