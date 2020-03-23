package com.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ElementDaoImpl implements ElementDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public String generateQuery(String element, String id, String name) {

        //The substring and uppercase is to match the model name
        String query = "SELECT u FROM "+element.substring(0, 1).toUpperCase() + element.substring(1)+" u ";
        //Checks if id field is empty
        if(!"".equals(id)){
            //Checks if name fiels is empty to add an AND after the query
            if(!"".equals(name)) query += "WHERE u."+element+"Id LIKE '%"+id+"%' AND ";
            else query += "WHERE LOWER(u."+element+"Id) LIKE '%"+id.toLowerCase()+"%' ";
        }

        //Checks if name field is empty
        if(!"".equals(name)) {
            //Checks if the typology is User
            if ("user".equals(element)) query += "WHERE LOWER(u.username) LIKE '%" + name.toLowerCase() + "%' ";
            else query += "WHERE LOWER(u.name) LIKE '%" + name.toLowerCase() + "%' ";
        }

        return query;
    }
}
