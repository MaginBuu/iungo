package com.dao;

import com.model.Incidence;

public interface IncidenceDao {

    void addIncidence(Incidence incidence);

    Incidence getIncidenceById(String id);

    Incidence getIncidenceByProcedureId(String id);
}
