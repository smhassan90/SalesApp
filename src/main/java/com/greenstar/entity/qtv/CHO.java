package com.greenstar.entity.qtv;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Syed Muhammad Hassan
 * 24/06/2019
 */

@Entity
@Table(name="HS_CHO")
public class CHO {

    @Id
    @Column(name="STAFF_CODE")
    private String staffCode;

    @Column(name="NAME")
    private String name;

    @Column(name="TERRITORY_CODE")
    private String territoryCode;

    @Column(name="isQTVAllowed")
    private int isQTVAllowed;

    @Column(name="isQATAllowed")
    private int isQATAllowed;

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTerritoryCode() {
        return territoryCode;
    }

    public void setTerritoryCode(String territoryCode) {
        this.territoryCode = territoryCode;
    }

    public int getIsQTVAllowed() {
        return isQTVAllowed;
    }

    public void setIsQTVAllowed(int isQTVAllowed) {
        this.isQTVAllowed = isQTVAllowed;
    }

    public int getIsQATAllowed() {
        return isQATAllowed;
    }

    public void setIsQATAllowed(int isQATAllowed) {
        this.isQATAllowed = isQATAllowed;
    }
}
