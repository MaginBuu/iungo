package com.dao;

import com.model.Procedure;

import java.util.List;

public interface ProcedureDao {

    void addProcedure(Procedure procedure);

    Procedure findById(String id);
}
