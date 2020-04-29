package com.service;

import com.model.Incidence;

public interface IncidenceService {

    void addIncidence(Incidence incidence);

    Incidence getIncidenceById(String id);

    Incidence getIncidenceByProcedureId(String id);
}
