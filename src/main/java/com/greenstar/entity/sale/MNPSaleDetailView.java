package com.greenstar.entity.sale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Syed Muhammad Hassan
 * 28th April, 2021
 */
@Entity
@Table(name="MNP_SALE_DETAIL")
public class MNPSaleDetailView {
    @Id
    @Column(name="HUID")
    private String HUID;
    @Column(name="POSITION_CODE")
    private String POSITION_CODE;
    @Column(name="SASCODE")
    private String SASCODE;
    @Column(name="EMPLOYEEID")
    private String EMPLOYEEID;
    @Column(name="TEAMREGION")
    private String TEAMREGION;
    @Column(name="ZONE")
    private String ZONE;
    @Column(name="SUBZONE")
    private String SUBZONE;
    @Column(name="DISTRICT")
    private String DISTRICT;
    @Column(name="TEHSIL")
    private String TEHSIL;
    @Column(name="PROVINCE")
    private String PROVINCE;
    @Column(name="REGION")
    private String REGION;
    @Column(name="TEAM")
    private String TEAM;
    @Column(name="CHANNEL")
    private String CHANNEL;
    @Column(name="PRODUCT")
    private String PRODUCT;
    @Column(name="GROUPON")
    private String GROUPON;
    @Column(name="GRP")
    private String GRP;
    @Column(name="GRP_CATEGORY")
    private String GRP_CATEGORY;
    @Column(name="PRODUCTGROUP")
    private String PRODUCTGROUP;
    @Column(name="E_QTY")
    private String E_QTY;
    @Column(name="GSM_CYP")
    private String GSM_CYP;
    @Column(name="NET_VALUE")
    private String NET_VALUE;
    @Column(name="NET_QTY")
    private String NET_QTY;
    @Column(name="PROVIDERCODE")
    private String PROVIDERCODE;

    public String getGROUPON() {
        return GROUPON;
    }

    public void setGROUPON(String GROUPON) {
        this.GROUPON = GROUPON;
    }

    public String getHUID() {
        return HUID;
    }

    public void setHUID(String HUID) {
        this.HUID = HUID;
    }

    public String getPOSITION_CODE() {
        return POSITION_CODE;
    }

    public void setPOSITION_CODE(String POSITION_CODE) {
        this.POSITION_CODE = POSITION_CODE;
    }

    public String getSASCODE() {
        return SASCODE;
    }

    public void setSASCODE(String SASCODE) {
        this.SASCODE = SASCODE;
    }

    public String getEMPLOYEEID() {
        return EMPLOYEEID;
    }

    public void setEMPLOYEEID(String EMPLOYEEID) {
        this.EMPLOYEEID = EMPLOYEEID;
    }

    public String getTEAMREGION() {
        return TEAMREGION;
    }

    public void setTEAMREGION(String TEAMREGION) {
        this.TEAMREGION = TEAMREGION;
    }

    public String getZONE() {
        return ZONE;
    }

    public void setZONE(String ZONE) {
        this.ZONE = ZONE;
    }

    public String getSUBZONE() {
        return SUBZONE;
    }

    public void setSUBZONE(String SUBZONE) {
        this.SUBZONE = SUBZONE;
    }

    public String getDISTRICT() {
        return DISTRICT;
    }

    public void setDISTRICT(String DISTRICT) {
        this.DISTRICT = DISTRICT;
    }

    public String getTEHSIL() {
        return TEHSIL;
    }

    public void setTEHSIL(String TEHSIL) {
        this.TEHSIL = TEHSIL;
    }

    public String getPROVINCE() {
        return PROVINCE;
    }

    public void setPROVINCE(String PROVINCE) {
        this.PROVINCE = PROVINCE;
    }

    public String getREGION() {
        return REGION;
    }

    public void setREGION(String REGION) {
        this.REGION = REGION;
    }

    public String getTEAM() {
        return TEAM;
    }

    public void setTEAM(String TEAM) {
        this.TEAM = TEAM;
    }

    public String getCHANNEL() {
        return CHANNEL;
    }

    public void setCHANNEL(String CHANNEL) {
        this.CHANNEL = CHANNEL;
    }

    public String getGRP() {
        return GRP;
    }

    public void setGRP(String GRP) {
        this.GRP = GRP;
    }

    public String getGRP_CATEGORY() {
        return GRP_CATEGORY;
    }

    public void setGRP_CATEGORY(String GRP_CATEGORY) {
        this.GRP_CATEGORY = GRP_CATEGORY;
    }

    public String getPRODUCTGROUP() {
        return PRODUCTGROUP;
    }

    public void setPRODUCTGROUP(String PRODUCTGROUP) {
        this.PRODUCTGROUP = PRODUCTGROUP;
    }

    public String getE_QTY() {
        return E_QTY;
    }

    public void setE_QTY(String e_QTY) {
        E_QTY = e_QTY;
    }

    public String getGSM_CYP() {
        return GSM_CYP;
    }

    public void setGSM_CYP(String GSM_CYP) {
        this.GSM_CYP = GSM_CYP;
    }

    public String getNET_VALUE() {
        return NET_VALUE;
    }

    public void setNET_VALUE(String NET_VALUE) {
        this.NET_VALUE = NET_VALUE;
    }

    public String getNET_QTY() {
        return NET_QTY;
    }

    public void setNET_QTY(String NET_QTY) {
        this.NET_QTY = NET_QTY;
    }

    public String getPROVIDERCODE() {
        return PROVIDERCODE;
    }

    public void setPROVIDERCODE(String PROVIDERCODE) {
        this.PROVIDERCODE = PROVIDERCODE;
    }

    public String getPRODUCT() {
        return PRODUCT;
    }

    public void setPRODUCT(String PRODUCT) {
        this.PRODUCT = PRODUCT;
    }
}
