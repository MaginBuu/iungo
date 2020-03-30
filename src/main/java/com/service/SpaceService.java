package com.service;


import com.model.Space;

import java.util.List;

public interface SpaceService {

    void addSpace(Space space);

    List<Space> getQueryResults(String query);

    Space getByIdWithTimeline(String id);

    Space getByIdWithTimelineDay(String id, int day);

    List<Space> getAll();
}
