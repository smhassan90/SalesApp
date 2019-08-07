package com.greenstar.dal;

import java.sql.Date;

/**
 * @author Syed Muhammad Hassan
 * 2-AUG-2019
 */
public class ApprovalQTVForm {

    private long id;
    /*
    Status = 0 means Pending
    Status = 1 means successfully synced
     */
    private int status;
    private Date visitDate;
    private String providerCode;
    private String providerName;
    private int approvalStatus;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
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

    public int getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(int approvalStatus) {
        this.approvalStatus = approvalStatus;
    }
}
