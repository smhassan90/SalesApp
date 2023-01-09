package com.greenstar.dal;

import com.greenstar.entity.crb.DropdownCRBData;
import com.greenstar.entity.eagle.Areas;
import com.greenstar.entity.eagle.CRForm;
import com.greenstar.entity.eagle.Questions;

import java.io.Serializable;
import java.util.List;

public class EagleData implements Serializable {
    private String sitaraBajiName;
    private String sitaraBajiCode;
    private String AMName;
    private String AMCode;
    private String region;
    private String district;
    private String providerCode;
    private String providerName;
    private Dashboard dashboard;
    private int type;
    List<DropdownCRBData> dropdownCRBData;
    List<CRForm> crForms;
    List<Areas> areas;
    List<Questions> questions;

    public String getSitaraBajiName() {
        return sitaraBajiName;
    }

    public void setSitaraBajiName(String sitaraBajiName) {
        this.sitaraBajiName = sitaraBajiName;
    }

    public String getSitaraBajiCode() {
        return sitaraBajiCode;
    }

    public void setSitaraBajiCode(String sitaraBajiCode) {
        this.sitaraBajiCode = sitaraBajiCode;
    }

    public String getAMName() {
        return AMName;
    }

    public void setAMName(String AMName) {
        this.AMName = AMName;
    }

    public String getAMCode() {
        return AMCode;
    }

    public void setAMCode(String AMCode) {
        this.AMCode = AMCode;
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

    public List<DropdownCRBData> getDropdownCRBData() {
        return dropdownCRBData;
    }

    public void setDropdownCRBData(List<DropdownCRBData> dropdownCRBData) {
        this.dropdownCRBData = dropdownCRBData;
    }

    public Dashboard getDashboard() {
        return dashboard;
    }

    public void setDashboard(Dashboard dashboard) {
        this.dashboard = dashboard;
    }

    public List<CRForm> getCrForms() {
        return crForms;
    }

    public void setCrForms(List<CRForm> crForms) {
        this.crForms = crForms;
    }

    public List<Areas> getAreas() {
        return areas;
    }

    public void setAreas(List<Areas> areas) {
        this.areas = areas;
    }

    public List<Questions> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Questions> questions) {
        this.questions = questions;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
