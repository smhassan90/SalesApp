package com.greenstar.dal;

import java.util.Date;

/**
 * @author Syed Muhammad Hassan
 * 21-APR-2020
 */
public class ApprovalQATForm {
    private long id;
    private String providerCode;
    private int approvalStatus;
    private Date dateOfAssessment;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProviderCode() {
        return providerCode;
    }

    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }

    public int getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(int approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public Date getDateOfAssessment() {
        return dateOfAssessment;
    }

    public void setDateOfAssessment(Date dateOfAssessment) {
        this.dateOfAssessment = dateOfAssessment;
    }
}
