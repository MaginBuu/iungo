package com.dao;

import com.model.Comment;
import com.model.Incidence;

import java.util.List;

public interface IncidenceDao {

    void addIncidence(Incidence incidence);

    Incidence getIncidenceById(String id);

    Incidence getIncidenceByProcedureId(String id);

    List<Incidence> getIncidenceByStudentId(String id);
    
    // COMMENT

    void addComment(Comment comment);

    Comment getCommentById(String id);
}
