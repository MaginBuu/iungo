package com.service;

import com.model.Group;

import java.util.List;

public interface GroupService {

    void addGroup(Group group);

    List<Group> getQueryResults(String query);
}
