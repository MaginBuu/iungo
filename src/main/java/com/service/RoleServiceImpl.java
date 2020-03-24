package com.service;

import com.dao.RoleDao;
import com.model.RoleClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDao roleDao;

    public void addRole(RoleClass role){
        roleDao.addRole(role);
    }
}
