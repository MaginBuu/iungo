package com.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Table(name = "anti_bullying_reports")
@NamedQueries({
        @NamedQuery(name = "AntiBullyingReport.findById", query = "SELECT r FROM AntiBullyingReport r  WHERE r.reportId = :id"),

})
public class AntiBullyingReport {

    private static final long serialVersionUID = 2681531852204068105L;

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "ANTI_BULLYING_REPORT_ID")
    private String reportId;

    @Column(name = "OBSERVED")
    private boolean observed;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "ANONYMOUS")
    private boolean anonymous;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "USER_ID")
    private User user;

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

    public boolean isAnonymous() {
        return anonymous;
    }

    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString(){
        String string = "";
        string += "Personal: " + !observed + "\n";
        if(anonymous)
            string += "From: anonymous\n";
        else
            string += "From: " + user.getFullName() + "\n";
        string += "Description: " + description;

        return string;
    }
}
