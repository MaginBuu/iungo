package com.service;

import com.model.Comment;
import com.model.Incidence;

public interface IncidenceService {

    void addIncidence(Incidence incidence);

    Incidence getIncidenceById(String id);

    Incidence getIncidenceByProcedureId(String id);
    
    // COMMENT

    void addComment(Comment comment);

    Comment getCommentById(String id);
}
