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
@Table(name="eagle_client_registration")
public class CRForm {
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

    /*
    Diarrhea Section
     */
    @Column(name="currentDiarrhea")
    private int currentDiarrhea;
    @Column(name="isMedicineProvided")
    private int isMedicineProvided;
    @Column(name="isCounseling")
    private int isCounseling;

    /*
    Diarrhea Section Ends
     */

    /*
    FP Planning Category
     */
    @Column(name="isCurrentUser")
    private int isCurrentUser;
    @Column(name="currentFPMethod")
    private String currentFPMethod;
    @Column(name="periodOfUsingCurrentMethod")
    private int periodOfUsingCurrentMethod;
    @Column(name="isEverUser")
    private int isEverUser;
    @Column(name="everMethodUsed")
    private String everMethodUsed;
    @Column(name="reasonForDiscontinuation")
    private String reasonForDiscontinuation;
    @Column(name="reasonForNeverUser")
    private String reasonForNeverUser;

    /*
    FP Ends
     */
    @Column(name="isTokenGiven")
    private int isTokenGiven;
    @Column(name="followUpVisitDate")
    private Date followUpVisitDate;
    @Column(name="approvalStatus")
    private int approvalStatus ;
    @Column(name="reportingMonth")
    private String reportingMonth;

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

    public int getCurrentDiarrhea() {
        return currentDiarrhea;
    }

    public void setCurrentDiarrhea(int currentDiarrhea) {
        this.currentDiarrhea = currentDiarrhea;
    }

    public int getIsMedicineProvided() {
        return isMedicineProvided;
    }

    public void setIsMedicineProvided(int isMedicineProvided) {
        this.isMedicineProvided = isMedicineProvided;
    }

    public int getIsCounseling() {
        return isCounseling;
    }

    public void setIsCounseling(int isCounseling) {
        this.isCounseling = isCounseling;
    }

    public int getIsCurrentUser() {
        return isCurrentUser;
    }

    public void setIsCurrentUser(int isCurrentUser) {
        this.isCurrentUser = isCurrentUser;
    }

    public String getCurrentFPMethod() {
        return currentFPMethod;
    }

    public void setCurrentFPMethod(String currentFPMethod) {
        this.currentFPMethod = currentFPMethod;
    }

    public int getPeriodOfUsingCurrentMethod() {
        return periodOfUsingCurrentMethod;
    }

    public void setPeriodOfUsingCurrentMethod(int periodOfUsingCurrentMethod) {
        this.periodOfUsingCurrentMethod = periodOfUsingCurrentMethod;
    }

    public int getIsEverUser() {
        return isEverUser;
    }

    public void setIsEverUser(int isEverUser) {
        this.isEverUser = isEverUser;
    }

    public String getEverMethodUsed() {
        return everMethodUsed;
    }

    public void setEverMethodUsed(String everMethodUsed) {
        this.everMethodUsed = everMethodUsed;
    }

    public String getReasonForDiscontinuation() {
        return reasonForDiscontinuation;
    }

    public void setReasonForDiscontinuation(String reasonForDiscontinuation) {
        this.reasonForDiscontinuation = reasonForDiscontinuation;
    }

    public String getReasonForNeverUser() {
        return reasonForNeverUser;
    }

    public void setReasonForNeverUser(String reasonForNeverUser) {
        this.reasonForNeverUser = reasonForNeverUser;
    }

    public int getIsTokenGiven() {
        return isTokenGiven;
    }

    public void setIsTokenGiven(int isTokenGiven) {
        this.isTokenGiven = isTokenGiven;
    }

    public Date getFollowUpVisitDate() {
        return followUpVisitDate;
    }

    public void setFollowUpVisitDate(Date followUpVisitDate) {
        this.followUpVisitDate = followUpVisitDate;
    }

    public int getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(int approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getReportingMonth() {
        return reportingMonth;
    }

    public void setReportingMonth(String reportingMonth) {
        this.reportingMonth = reportingMonth;
    }
}
