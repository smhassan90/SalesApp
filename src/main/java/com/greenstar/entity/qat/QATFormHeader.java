package com.greenstar.entity.qat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

/**
 * @author Syed Muhammad Hassan
 * 4th April, 2020
 */

@Entity
@Table(name="QAT_FormHeader")
public class QATFormHeader {

    @Id
    @Column(name="ID")
    private long id;

    @Column(name="supervisorCode")
    private String supervisorCode;

    @Column(name="supervisorName")
    private String supervisorName;

    @Column(name="QAMCode")
    private String QAMCode;

    @Column(name="QAMName")
    private String QAMName;

    @Column(name="dateOfAssessment")
    private Date dateOfAssessment;

    @Column(name="providerCode")
    private String providerCode;

    @Column(name="providerName")
    private String providerName;

    @Column(name="region")
    private String region;

    @Column(name="mobileDate")
    private String mobileDate;

    @Column(name="approvalStatus")
    private int approvalStatus;

    @Column(name="providerDonor")
    private String providerDonor;

    @Column(name="reportingMonth")
    private String reportingMonth;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSupervisorCode() {
        return supervisorCode;
    }

    public void setSupervisorCode(String supervisorCode) {
        this.supervisorCode = supervisorCode;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public String getQAMCode() {
        return QAMCode;
    }

    public void setQAMCode(String QAMCode) {
        this.QAMCode = QAMCode;
    }

    public String getQAMName() {
        return QAMName;
    }

    public void setQAMName(String QAMName) {
        this.QAMName = QAMName;
    }

    public Date getDateOfAssessment() {
        return dateOfAssessment;
    }

    public void setDateOfAssessment(Date dateOfAssessment) {
        this.dateOfAssessment = dateOfAssessment;
    }

    public String getProviderCode() {
        return providerCode;
    }

    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getMobileDate() {
        return mobileDate;
    }

    public void setMobileDate(String mobileDate) {
        this.mobileDate = mobileDate;
    }

    public int getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(int approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getProviderDonor() {
        return providerDonor;
    }

    public void setProviderDonor(String providerDonor) {
        this.providerDonor = providerDonor;
    }

    public String getReportingMonth() {
        return reportingMonth;
    }

    public void setReportingMonth(String reportingMonth) {
        this.reportingMonth = reportingMonth;
    }
}
