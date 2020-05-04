package com.service;

import com.dao.ProcedureDao;
import com.dao.UserDao;
import com.model.Notification;
import com.model.Procedure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class ProcedureServiceImpl implements ProcedureService {

    @Autowired
    ProcedureDao procedureDao;

    @Autowired
    UserDao userDao;


    public void addProcedure(Procedure procedure){

        procedureDao.addProcedure(procedure);
        String title = "New procedure added";
        String description = procedure.getTitle();
        userDao.addNotification(new Notification(procedure.getUserP(), title, description, true, new Date()));
    }

    public Procedure findById(String id){ return procedureDao.findById(id); }
}
