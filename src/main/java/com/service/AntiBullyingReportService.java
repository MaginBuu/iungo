package com.service;

import com.model.AntiBullyingReport;

public interface AntiBullyingReportService {

    void addAntiBullyingReport(AntiBullyingReport report);

    void deleteAntiBullyingReport(AntiBullyingReport report);

    AntiBullyingReport getById(String id);
}
