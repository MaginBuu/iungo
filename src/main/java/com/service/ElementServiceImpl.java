package com.service;

import com.dao.ElementDao;
import org.springframework.beans.factory.annotation.Autowired;

public class ElementServiceImpl implements ElementService{

    @Autowired
    ElementDao elementDao;

    public String generateQuery(String element, String id, String name){ return elementDao.generateQuery(element, id, name); }
}
