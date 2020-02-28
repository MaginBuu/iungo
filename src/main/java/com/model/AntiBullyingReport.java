package com.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Table(name = "anti_bullying_reports")
public class AntiBullyingReport {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "ANTI_BULLYING_REPORT_ID")
    private String reportId;

    @Column(name = "OBSERVED")
    private boolean observed;

    @Column(name = "DESCRIPTION")
    private String description;

    public AntiBullyingReport() {}

    public AntiBullyingReport(boolean observed, String description) {
        this.observed = observed;
        this.description = description;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public boolean isObserved() {
        return observed;
    }

    public void setObserved(boolean observed) {
        this.observed = observed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
