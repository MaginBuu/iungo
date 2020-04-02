package com.service;

import com.dao.TimeLineDao;
import com.model.TimeLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TimeLineServiceImpl implements TimeLineService {

    @Autowired
    TimeLineDao timeLineDao;

    public void addTimeLine(TimeLine timeline){
        timeLineDao.addTimeLine(timeline);
    }

    public void deleteTimeLine(TimeLine timeline) { timeLineDao.deleteTimeLine(timeline); }

    public TimeLine getById(String id) { return timeLineDao.getById(id); }
}
