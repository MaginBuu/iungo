package com.service;

import com.dao.AntiBullyingReportDao;
import com.model.AntiBullyingReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AntiBullyingReportServiceImpl implements AntiBullyingReportService {

    @Autowired
    AntiBullyingReportDao antiBullyingReportDao;

    public void addAntiBullyingReport(AntiBullyingReport report){
        antiBullyingReportDao.addAntiBullyingReport(report);
    }

    public void deleteAntiBullyingReport(AntiBullyingReport report) { antiBullyingReportDao.deleteAntiBullyingReport(report); }

    public AntiBullyingReport getById(String id) { return antiBullyingReportDao.getById(id); }
}
