package com.greenstar.entity.eagle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

/**
 * @author Syed Muhammad Hassan
 * 13/10/2021
 */

@Entity
@Table(name="eagle_neighbour_form")
public class NeighbourForm {
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
    @Column(name="meetingType")
    private int meetingType;
    @Column(name="communityName")
    private String communityName;
    @Column(name="reportingMonth")
    private String reportingMonth;

    @Column(name="latLong")
    private String latLong;

    @Column(name="mobileSystemDate")
    private String mobileSystemDate;

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

    public int getMeetingType() {
        return meetingType;
    }

    public void setMeetingType(int meetingType) {
        this.meetingType = meetingType;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
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
}