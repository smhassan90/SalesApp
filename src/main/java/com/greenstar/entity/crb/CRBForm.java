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

    @Column(name="husbandName")
    private String husbandName;

    @Column(name="clientAge")
    private String clientAge;

    @Column(name="address")
    private String address;

    @Column(name="contactNumber")
    private String contactNumber;

    @Column(name="canWeContact")
    private int canWeContact;

    @Column(name="durationOfMarriage")
    private double durationOfMarriage;

    @Column(name="noOfSons")
    private int noOfSons;

    @Column(name="noOfDaughters")
    private int noOfDaughters;

    @Column(name="numberOfAbortion")
    private int numberOfAbortion;

    @Column(name="referredBy")
    private String referredBy;

    @Column(name="ipcReferralStatus")
    private int ipcReferralStatus;

    @Column(name="isEverUser")
    private int isEverUser;

    //More 1
    //Less 0
    @Column(name="methodNotInUse")
    private int methodNotInUse;

    @Column(name="isCurrentUser")
    private int isCurrentUser;

    @Column(name="currentUseYear")
    private double currentUseYear;

    @Column(name="currentMethod")
    private String currentMethod;

    @Column(name="timingOfService")
    private String timingOfService;

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

    public String getHusbandName() {
        return husbandName;
    }

    public void setHusbandName(String husbandName) {
        this.husbandName = husbandName;
    }

    public String getClientAge() {
        return clientAge;
    }

    public void setClientAge(String clientAge) {
        this.clientAge = clientAge;
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

    public int getCanWeContact() {
        return canWeContact;
    }

    public void setCanWeContact(int canWeContact) {
        this.canWeContact = canWeContact;
    }

    public double getDurationOfMarriage() {
        return durationOfMarriage;
    }

    public void setDurationOfMarriage(double durationOfMarriage) {
        this.durationOfMarriage = durationOfMarriage;
    }

    public int getNoOfSons() {
        return noOfSons;
    }

    public void setNoOfSons(int noOfSons) {
        this.noOfSons = noOfSons;
    }

    public int getNoOfDaughters() {
        return noOfDaughters;
    }

    public void setNoOfDaughters(int noOfDaughters) {
        this.noOfDaughters = noOfDaughters;
    }

    public int getNumberOfAbortion() {
        return numberOfAbortion;
    }

    public void setNumberOfAbortion(int numberOfAbortion) {
        this.numberOfAbortion = numberOfAbortion;
    }

    public String getReferredBy() {
        return referredBy;
    }

    public void setReferredBy(String referredBy) {
        this.referredBy = referredBy;
    }

    public int getIpcReferralStatus() {
        return ipcReferralStatus;
    }

    public void setIpcReferralStatus(int ipcReferralStatus) {
        this.ipcReferralStatus = ipcReferralStatus;
    }

    public int getIsEverUser() {
        return isEverUser;
    }

    public void setIsEverUser(int isEverUser) {
        this.isEverUser = isEverUser;
    }

    public int getMethodNotInUse() {
        return methodNotInUse;
    }

    public void setMethodNotInUse(int methodNotInUse) {
        this.methodNotInUse = methodNotInUse;
    }

    public int getIsCurrentUser() {
        return isCurrentUser;
    }

    public void setIsCurrentUser(int isCurrentUser) {
        this.isCurrentUser = isCurrentUser;
    }

    public double getCurrentUseYear() {
        return currentUseYear;
    }

    public void setCurrentUseYear(double currentUseYear) {
        this.currentUseYear = currentUseYear;
    }

    public String getCurrentMethod() {
        return currentMethod;
    }

    public void setCurrentMethod(String currentMethod) {
        this.currentMethod = currentMethod;
    }

    public String getTimingOfService() {
        return timingOfService;
    }

    public void setTimingOfService(String timingOfService) {
        this.timingOfService = timingOfService;
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
}
