package com.greenstar.entity.crb;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Syed Muhammad Hassan
 * 29th November, 2019
 */

@Entity
@Table(name="CRB_FORM_ANDROID")
public class CRBForm {

    @Id
    @Column(name="ID")
    private long id;

    @Column(name="visitDate")
    private String visitDate;

    @Column(name="clientName")
    private String clientName;

    @Column(name="address")
    private String address;

    @Column(name="contactNumber")
    private String contactNumber;

    @Column(name="noOfChildren")
    private int noOfChildren;

    @Column(name="referredBy")
    private String referredBy;

    @Column(name="clientAge")
    private String clientAge;

    @Column(name="fpUserCategory")
    private String fpUserCategory;

    @Column(name="currentMethod")
    private String currentMethod;

    @Column(name="timingFPService")
    private String timingFPService;

    @Column(name="serviceType")
    private String serviceType;

    @Column(name="methodWithin12Months")
    private int methodWithin12Months ;

    @Column(name="providerCode")
    private String providerCode;

    @Column(name="providerName")
    private String providerName ;

    @Column(name="status")
    private int status ;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public int getNoOfChildren() {
        return noOfChildren;
    }

    public void setNoOfChildren(int noOfChildren) {
        this.noOfChildren = noOfChildren;
    }

    public String getReferredBy() {
        return referredBy;
    }

    public void setReferredBy(String referredBy) {
        this.referredBy = referredBy;
    }

    public String getClientAge() {
        return clientAge;
    }

    public void setClientAge(String clientAge) {
        this.clientAge = clientAge;
    }

    public String getCurrentMethod() {
        return currentMethod;
    }

    public void setCurrentMethod(String currentMethod) {
        this.currentMethod = currentMethod;
    }

    public String getTimingFPService() {
        return timingFPService;
    }

    public void setTimingFPService(String timingFPService) {
        this.timingFPService = timingFPService;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public int getMethodWithin12Months() {
        return methodWithin12Months;
    }

    public void setMethodWithin12Months(int methodWithin12Months) {
        this.methodWithin12Months = methodWithin12Months;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getFpUserCategory() {
        return fpUserCategory;
    }

    public void setFpUserCategory(String fpUserCategory) {
        this.fpUserCategory = fpUserCategory;
    }
}
