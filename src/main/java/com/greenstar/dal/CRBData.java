package com.greenstar.dal;

import com.greenstar.entity.crb.CRBForm;
import com.greenstar.entity.crb.DropdownCRBData;

import java.util.List;

/**
 * @author Syed Muhammad Hassan
 * 29th November, 2019
 */
public class CRBData {
    private String name;
    private String code;

    List<CRBForm> crbForms;
    List<DropdownCRBData> dropdownCRBData;
    Dashboard dashboard;

    public Dashboard getDashboard() {
        return dashboard;
    }

    public void setDashboard(Dashboard dashboard) {
        this.dashboard = dashboard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<CRBForm> getCrbForms() {
        return crbForms;
    }

    public void setCrbForms(List<CRBForm> crbForms) {
        this.crbForms = crbForms;
    }

    public List<DropdownCRBData> getDropdownCRBData() {
        return dropdownCRBData;
    }

    public void setDropdownCRBData(List<DropdownCRBData> dropdownCRBData) {
        this.dropdownCRBData = dropdownCRBData;
    }
}
