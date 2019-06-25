package com.greenstar.entity;

import javax.persistence.*;

@Entity
@Table(name="GSS_STAFF")
public class GSSStaff {
    @Id
    @Column(name="STAFF_CODE")
    private String staffCode;

    @Column(name="STAFF_NAME")
    private String staffName;

    @Column(name="status")
    private int status;

    @Column(name="token")
    private String token;

    @Column(name="staff_type")
    private int staffType;

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getStaffType() {
        return staffType;
    }

    public void setStaffType(int staffType) {
        this.staffType = staffType;
    }
}
