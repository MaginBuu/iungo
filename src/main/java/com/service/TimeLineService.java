package com.service;

import com.model.TimeLine;

public interface TimeLineService {

    void addTimeLine(TimeLine timeline);

    void deleteTimeLine(TimeLine timeline);

    TimeLine getById(String id);
}
