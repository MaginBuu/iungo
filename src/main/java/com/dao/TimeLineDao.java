package com.dao;

import com.model.TimeLine;

public interface TimeLineDao {


    void addTimeLine(TimeLine timeline);

    void deleteTimeLine(TimeLine timeline);

    TimeLine getById(String id);
}
