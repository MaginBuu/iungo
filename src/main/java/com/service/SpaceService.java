package com.service;


import com.model.Space;

import java.util.List;

public interface SpaceService {

    void addSpace(Space space);
    List<Space> getQueryResults(String query);
    Space getSpaceById(String id);
}
