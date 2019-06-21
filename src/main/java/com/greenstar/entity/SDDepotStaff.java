package com.greenstar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SD_DEPO_STAFF")
public class SDDepotStaff {
    @Id
    @Column(name="DEPO_ID")
    private String depotId;

    @Column(name="STAFF_CODE")
    private String staffCode;

    public String getDepotId() {
        return depotId;
    }

    public void setDepotId(String depotId) {
        this.depotId = depotId;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }
}
