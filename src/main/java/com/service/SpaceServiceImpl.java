package com.service;

import com.dao.SpaceDao;
import com.model.Space;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SpaceServiceImpl implements SpaceService {

    @Autowired
    SpaceDao spaceDao;

    public void addSpace(Space space){
        spaceDao.addSpace(space);
    }
}
