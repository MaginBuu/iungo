package com.service;

import com.dao.AntiBullyingReportDao;
import com.model.AntiBullyingReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AntiBullyingReportServiceImpl implements AntiBullyingReportService {

    @Autowired
    AntiBullyingReportDao timeLineDao;

    public void addAntiBullyingReport(AntiBullyingReport report){
        timeLineDao.addAntiBullyingReport(report);
    }

    public void deleteAntiBullyingReport(AntiBullyingReport report) { timeLineDao.deleteAntiBullyingReport(report); }

    public AntiBullyingReport getById(String id) { return timeLineDao.getById(id); }
}
