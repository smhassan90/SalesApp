package com.greenstar.dal;

import com.greenstar.entity.SDDepot;
import com.greenstar.entity.qtv.Providers;

import java.io.Serializable;
import java.util.List;

/**
 * @author Syed Muhammad Hassan
 * 24/06/2019
 */
public class HSData implements Serializable {
    private String name;
    private String code;
    private String AMName;
    private String AMCode;
    private String region;
    private Dashboard dashboard;

    List<ApprovalQTVForm> qtvForms;
    List<Providers> providers;

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

    public List<Providers> getProviders() {
        return providers;
    }

    public void setProviders(List<Providers> providers) {
        this.providers = providers;
    }

    public List<ApprovalQTVForm> getQtvForms() {
        return qtvForms;
    }

    public void setQtvForms(List<ApprovalQTVForm> qtvForms) {
        this.qtvForms = qtvForms;
    }

    public Dashboard getDashboard() {
        return dashboard;
    }

    public void setDashboard(Dashboard dashboard) {
        this.dashboard = dashboard;
    }
}
