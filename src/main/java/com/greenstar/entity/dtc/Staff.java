package com.greenstar.entity.dtc;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Syed Muhammad Hassan
 * 17/09/2019
 */

@Entity
@Table(name="DTC_STAFF")
public class Staff {
    @Id
    @Column(name="STAFF_CODE")
    private String staffCode;
    @Column(name="STAFF_NAME")
    private String staffName;

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }
}
