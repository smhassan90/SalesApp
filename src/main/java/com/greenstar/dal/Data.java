package com.greenstar.dal;

import com.greenstar.entity.*;

import java.io.Serializable;
import java.util.List;

public class Data implements Serializable {
    List<SDDepot> depots;
    List<SDTown> towns;
    List<SDCustomer> customers;
    List<SDTownDepot> townDepots;
    List<SDTownCustomer> townCustomers;
    List<SDStatus> statuses;
    List<SDSKUGroup> skuGroup;
    List<GSSWorkWith> workWiths;
    Dashboard dashboard;

    public List<SDDepot> getDepots() {
        return depots;
    }

    public void setDepots(List<SDDepot> depots) {
        this.depots = depots;
    }

    public List<SDTown> getTowns() {
        return towns;
    }

    public void setTowns(List<SDTown> towns) {
        this.towns = towns;
    }

    public List<SDCustomer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<SDCustomer> customers) {
        this.customers = customers;
    }

    public List<SDTownDepot> getTownDepots() {
        return townDepots;
    }

    public void setTownDepots(List<SDTownDepot> townDepots) {
        this.townDepots = townDepots;
    }

    public List<SDTownCustomer> getTownCustomers() {
        return townCustomers;
    }

    public void setTownCustomers(List<SDTownCustomer> townCustomers) {
        this.townCustomers = townCustomers;
    }

    public List<SDStatus> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<SDStatus> statuses) {
        this.statuses = statuses;
    }

    public List<SDSKUGroup> getSkuGroup() {
        return skuGroup;
    }

    public void setSkuGroup(List<SDSKUGroup> skuGroup) {
        this.skuGroup = skuGroup;
    }

    public List<GSSWorkWith> getWorkWiths() {
        return workWiths;
    }

    public void setWorkWiths(List<GSSWorkWith> workWiths) {
        this.workWiths = workWiths;
    }

    public Dashboard getDashboard() {
        return dashboard;
    }

    public void setDashboard(Dashboard dashboard) {
        this.dashboard = dashboard;
    }
}
