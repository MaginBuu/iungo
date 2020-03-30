package com.service;

import com.dao.SpaceDao;
import com.model.Space;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SpaceServiceImpl implements SpaceService {

    @Autowired
    SpaceDao spaceDao;

    public void addSpace(Space space){
        spaceDao.addSpace(space);
    }

    public List<Space> getQueryResults(String query) { return spaceDao.getQueryResults(query); }

    public Space getByIdWithTimeline(String id) { return  spaceDao.getByIdWithTimeline(id); }

    public Space getByIdWithTimelineDay(String id, int day) { return spaceDao.getByIdWithTimelineDay(id, day); }

    public List<Space> getAll() { return spaceDao.getAll(); }
}
