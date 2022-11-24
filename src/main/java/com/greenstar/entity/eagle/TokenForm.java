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
@Table(name="eagle_token_form")
public class TokenForm {
    @Id
    @Column(name="ID")
    private long id;
    @Column(name="clientId")
    private long clientId;
    @Column(name="referralDate")
    private Date referralDate;
    @Column(name="referredMethod")
    private String referredMethod;
    @Column(name="providerCode")
    private String providerCode;
    @Column(name="sitarabajiCode")
    private String sitarabajiCode;
    @Column(name="redeemDate")
    private Date redeemDate;
    @Column(name="methodTaken")
    private String methodTaken;

    @Column(name="REPORTINGMONTH")
    private String reportingMonth;

    @Column(name="latLong")
    private String latLong;

    @Column(name="mobileSystemDate")
    private String mobileSystemDate;
    @Column(name="registeredAt")
    private String registeredAt;
    @Column(name="remarks")
    private String remarks;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public Date getReferralDate() {
        return referralDate;
    }

    public void setReferralDate(Date referralDate) {
        this.referralDate = referralDate;
    }

    public String getReferredMethod() {
        return referredMethod;
    }

    public void setReferredMethod(String referredMethod) {
        this.referredMethod = referredMethod;
    }

    public String getProviderCode() {
        return providerCode;
    }

    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }

    public String getSitarabajiCode() {
        return sitarabajiCode;
    }

    public void setSitarabajiCode(String sitarabajiCode) {
        this.sitarabajiCode = sitarabajiCode;
    }

    public Date getRedeemDate() {
        return redeemDate;
    }

    public void setRedeemDate(Date redeemDate) {
        this.redeemDate = redeemDate;
    }

    public String getMethodTaken() {
        return methodTaken;
    }

    public void setMethodTaken(String methodTaken) {
        this.methodTaken = methodTaken;
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

    public String getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(String registeredAt) {
        this.registeredAt = registeredAt;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
