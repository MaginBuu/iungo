package com.service;

import com.dao.ProcedureDao;
import com.model.Procedure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProcedureServiceImpl implements ProcedureService {

    @Autowired
    ProcedureDao procedureDao;

    public void addProcedure(Procedure procedure){
        procedureDao.addProcedure(procedure);
    }
}
