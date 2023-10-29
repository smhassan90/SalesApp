package com.greenstar.entity.eagle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

/**
 * @author Syed Muhammad Hassan
 * 12/10/2021
 */

@Entity
@Table(name="eagle_followup_form")
public class FollowupForm {
    @Id
    @Column(name="ID")
    private long id;
    @Column(name="sitarabajiCode")
    private String sitarabajiCode;
    @Column(name="sitarabajiName")
    private String sitarabajiName;
    @Column(name="providerCode")
    private String providerCode;
    @Column(name="providerName")
    private String providerName;
    @Column(name="region")
    private String region;
    @Column(name="district")
    private String district;
    @Column(name="tehsil")
    private String tehsil;
    @Column(name="supervisorCode")
    private String supervisorCode ;
    @Column(name="supervisorName")
    private String supervisorName ;
    @Column(name="visitDate")
    private Date visitDate;

    @Column(name="followupDate")
    private Date followupDate;
    @Column(name="clientId")
    private long clientId;
    @Column(name="didYouVisit")
    private int didYouVisit;
    @Column(name="isSupportSitaraHouse")
    private int isSupportSitaraHouse;
    @Column(name="isSupportProvider")
    private int isSupportProvider;
    @Column(name="isSupportCompleted")
    private int isSupportCompleted;
    @Column(name="reasonForNotVisiting")
    private String reasonForNotVisiting;
    @Column(name="haveYouAdopted")
    private int haveYouAdopted;
    @Column(name="adoptedMethod")
    private String service;

    @Column(name="price")
    private int price;
    @Column(name="anySideEffects")
    private int anySideEffects;
    @Column(name="didVisitAfterSideEffects")
    private int didVisitAfterSideEffects;
    @Column(name="reasonsForNotAdoptingMethod")
    private String reasonsForNotAdoptingMethod;
    @Column(name="reportingMonth")
    private String reportingMonth;

    @Column(name="latLong")
    private String latLong;

    @Column(name="mobileSystemDate")
    private String mobileSystemDate;

    @Column(name="remarks")
    private String remarks;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSitarabajiCode() {
        return sitarabajiCode;
    }

    public void setSitarabajiCode(String sitarabajiCode) {
        this.sitarabajiCode = sitarabajiCode;
    }

    public String getSitarabajiName() {
        return sitarabajiName;
    }

    public void setSitarabajiName(String sitarabajiName) {
        this.sitarabajiName = sitarabajiName;
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

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getTehsil() {
        return tehsil;
    }

    public void setTehsil(String tehsil) {
        this.tehsil = tehsil;
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

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public int getDidYouVisit() {
        return didYouVisit;
    }

    public void setDidYouVisit(int didYouVisit) {
        this.didYouVisit = didYouVisit;
    }

    public String getReasonForNotVisiting() {
        return reasonForNotVisiting;
    }

    public void setReasonForNotVisiting(String reasonForNotVisiting) {
        this.reasonForNotVisiting = reasonForNotVisiting;
    }

    public int getHaveYouAdopted() {
        return haveYouAdopted;
    }

    public void setHaveYouAdopted(int haveYouAdopted) {
        this.haveYouAdopted = haveYouAdopted;
    }

    public int getAnySideEffects() {
        return anySideEffects;
    }

    public void setAnySideEffects(int anySideEffects) {
        this.anySideEffects = anySideEffects;
    }

    public int getDidVisitAfterSideEffects() {
        return didVisitAfterSideEffects;
    }

    public void setDidVisitAfterSideEffects(int didVisitAfterSideEffects) {
        this.didVisitAfterSideEffects = didVisitAfterSideEffects;
    }

    public String getReasonsForNotAdoptingMethod() {
        return reasonsForNotAdoptingMethod;
    }

    public void setReasonsForNotAdoptingMethod(String reasonsForNotAdoptingMethod) {
        this.reasonsForNotAdoptingMethod = reasonsForNotAdoptingMethod;
    }

    public String getReportingMonth() {
        return reportingMonth;
    }

    public void setReportingMonth(String reportingMonth) {
        this.reportingMonth = reportingMonth;
    }

    public String getLatLong() {
        return latLong;
    }

    public void setLatLong(String latLong) {
        this.latLong = latLong;
    }

    public String getMobileSystemDate() {
        return mobileSystemDate;
    }

    public void setMobileSystemDate(String mobileSystemDate) {
        this.mobileSystemDate = mobileSystemDate;
    }

    public Date getFollowupDate() {
        return followupDate;
    }

    public void setFollowupDate(Date followupDate) {
        this.followupDate = followupDate;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public int getIsSupportSitaraHouse() {
        return isSupportSitaraHouse;
    }

    public void setIsSupportSitaraHouse(int isSupportSitaraHouse) {
        this.isSupportSitaraHouse = isSupportSitaraHouse;
    }

    public int getIsSupportProvider() {
        return isSupportProvider;
    }

    public void setIsSupportProvider(int isSupportProvider) {
        this.isSupportProvider = isSupportProvider;
    }

    public int getIsSupportCompleted() {
        return isSupportCompleted;
    }

    public void setIsSupportCompleted(int isSupportCompleted) {
        this.isSupportCompleted = isSupportCompleted;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
