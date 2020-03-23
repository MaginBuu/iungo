package com.service;

import com.dao.GroupDao;
import com.model.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    GroupDao groupDao;

    public void addGroup(Group group){
        groupDao.addGroup(group);
    }
}
