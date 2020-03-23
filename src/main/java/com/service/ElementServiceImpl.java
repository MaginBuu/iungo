package com.service;

import com.dao.ElementDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ElementServiceImpl implements ElementService{

    @Autowired
    ElementDao elementDao;

    public String generateQuery(String element, String id, String name){ return elementDao.generateQuery(element, id, name); }
}
