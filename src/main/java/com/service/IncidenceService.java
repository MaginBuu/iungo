package com.service;

import com.model.Incidence;

import java.util.List;

public interface IncidenceService {

    void addIncidence(Incidence incidence);

    Incidence getIncidenceById(String id);

    Incidence getIncidenceByProcedureId(String id);

    List<Incidence> getIncidenceByStudentId(String id);
}
