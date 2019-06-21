package com.greenstar.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SD_TOWN_STAFF")
public class SDTownStaff {

    @Id
    @Column(name="TOWN_ID")
    private String townId;

    @Column(name="STAFF_CODE")
    private String staffCode;

    public String getTownId() {
        return townId;
    }

    public void setTownId(String townId) {
        this.townId = townId;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }
}
