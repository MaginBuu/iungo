package com.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ElementDaoImpl implements ElementDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public String generateQuery(String element, String id, String name) {
        String query = "FROM "+element+"s ";
        if(!"".equals(id) && !"".equals(name)) query += "WHERE "+element+"Id LIKE '%"+id+"%' AND ";
        else if(!"".equals(id)) query += "WHERE "+element+"Id LIKE '%"+id+"%' ";
        if(!"".equals(name)) query += "WHERE name LIKE '%"+name+"%' ";
        return query;
    }
}
