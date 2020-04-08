package com.dao;

import com.model.AntiBullyingReport;

public interface AntiBullyingReportDao {


    void addAntiBullyingReport(AntiBullyingReport report);

    void deleteAntiBullyingReport(AntiBullyingReport report);

    AntiBullyingReport getById(String id);
}
