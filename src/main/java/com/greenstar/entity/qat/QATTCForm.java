package com.greenstar.entity.qat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

/**
 * @author Syed Muhammad Hassan
 * 21st May, 2020
 */

@Entity
@Table(name="QAT_TC_FORM")
public class QATTCForm {
    @Id
    @Column(name = "providerCode")
    private String providerCode;

    @Column(name = "supervisorCode")
    private String supervisorCode;

    @Column(name = "supervisorName")
    private String supervisorName;

    @Column(name = "QAMCode")
    private String QAMCode;

    @Column(name = "QAMName")
    private String QAMName;

    @Column(name = "dateOfAssessment")
    private Date dateOfAssessment;

    @Column(name = "providerName")
    private String providerName;

    @Column(name = "region")
    private String region;

    @Column(name = "mobileDate")
    private String mobileDate;

    @Column(name = "PCCD")
    private int PCCD;

    @Column(name = "CDPF")
    private int CDPF;

    @Column(name = "PCPNCC")
    private int PCPNCC;

    @Column(name = "PFPNCC")
    private int PFPNCC;

    @Column(name = "PCGTC")
    private int PCGTC;

    @Column(name = "GTCPF")
    private int GTCPF;

    @Column(name = "PCMiso")
    private int PCMiso;

    @Column(name = "PCMVA")
    private int PCMVA;

    @Column(name = "MVAPF")
    private int MVAPF;

    @Column(name = "PCPPIUCD")
    private int PCPPIUCD;

    @Column(name = "PPIUCDPF")
    private int PPIUCDPF;

    @Column(name = "PCImplant")
    private int PCImplant;

    @Column(name = "ImplantPF")
    private int ImplantPF;

    @Column(name = "PCLOA")
    private int PCLOA;

    @Column(name = "LOAPF")
    private int LOAPF;

    @Column(name = "PCPHCC")
    private int PCPHCC;

    @Column(name = "PHCCPF")
    private int PHCCPF;

    @Column(name = "PCQTVActionPlan")
    private int PCQTVActionPlan;

    @Column(name = "QTVActionPlanPF")
    private int QTVActionPlanPF;

    @Column(name = "PCRecruitmentForm")
    private int PCRecruitmentForm;

    @Column(name = "RecruitmentFormPF")
    private int RecruitmentFormPF;

    @Column(name = "approvalStatus")
    private int approvalStatus;

    public String getProviderCode() {
        return providerCode;
    }

    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
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

    public int getPCCD() {
        return PCCD;
    }

    public void setPCCD(int PCCD) {
        this.PCCD = PCCD;
    }

    public int getCDPF() {
        return CDPF;
    }

    public void setCDPF(int CDPF) {
        this.CDPF = CDPF;
    }

    public int getPCPNCC() {
        return PCPNCC;
    }

    public void setPCPNCC(int PCPNCC) {
        this.PCPNCC = PCPNCC;
    }

    public int getPFPNCC() {
        return PFPNCC;
    }

    public void setPFPNCC(int PFPNCC) {
        this.PFPNCC = PFPNCC;
    }

    public int getPCGTC() {
        return PCGTC;
    }

    public void setPCGTC(int PCGTC) {
        this.PCGTC = PCGTC;
    }

    public int getGTCPF() {
        return GTCPF;
    }

    public void setGTCPF(int GTCPF) {
        this.GTCPF = GTCPF;
    }

    public int getPCMiso() {
        return PCMiso;
    }

    public void setPCMiso(int PCMiso) {
        this.PCMiso = PCMiso;
    }

    public int getPCMVA() {
        return PCMVA;
    }

    public void setPCMVA(int PCMVA) {
        this.PCMVA = PCMVA;
    }

    public int getMVAPF() {
        return MVAPF;
    }

    public void setMVAPF(int MVAPF) {
        this.MVAPF = MVAPF;
    }

    public int getPCPPIUCD() {
        return PCPPIUCD;
    }

    public void setPCPPIUCD(int PCPPIUCD) {
        this.PCPPIUCD = PCPPIUCD;
    }

    public int getPPIUCDPF() {
        return PPIUCDPF;
    }

    public void setPPIUCDPF(int PPIUCDPF) {
        this.PPIUCDPF = PPIUCDPF;
    }

    public int getPCImplant() {
        return PCImplant;
    }

    public void setPCImplant(int PCImplant) {
        this.PCImplant = PCImplant;
    }

    public int getImplantPF() {
        return ImplantPF;
    }

    public void setImplantPF(int implantPF) {
        ImplantPF = implantPF;
    }

    public int getPCLOA() {
        return PCLOA;
    }

    public void setPCLOA(int PCLOA) {
        this.PCLOA = PCLOA;
    }

    public int getLOAPF() {
        return LOAPF;
    }

    public void setLOAPF(int LOAPF) {
        this.LOAPF = LOAPF;
    }

    public int getPCPHCC() {
        return PCPHCC;
    }

    public void setPCPHCC(int PCPHCC) {
        this.PCPHCC = PCPHCC;
    }

    public int getPHCCPF() {
        return PHCCPF;
    }

    public void setPHCCPF(int PHCCPF) {
        this.PHCCPF = PHCCPF;
    }

    public int getPCQTVActionPlan() {
        return PCQTVActionPlan;
    }

    public void setPCQTVActionPlan(int PCQTVActionPlan) {
        this.PCQTVActionPlan = PCQTVActionPlan;
    }

    public int getQTVActionPlanPF() {
        return QTVActionPlanPF;
    }

    public void setQTVActionPlanPF(int QTVActionPlanPF) {
        this.QTVActionPlanPF = QTVActionPlanPF;
    }

    public int getPCRecruitmentForm() {
        return PCRecruitmentForm;
    }

    public void setPCRecruitmentForm(int PCRecruitmentForm) {
        this.PCRecruitmentForm = PCRecruitmentForm;
    }

    public int getRecruitmentFormPF() {
        return RecruitmentFormPF;
    }

    public void setRecruitmentFormPF(int recruitmentFormPF) {
        RecruitmentFormPF = recruitmentFormPF;
    }

    public int getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(int approvalStatus) {
        this.approvalStatus = approvalStatus;
    }
}
