package com.greenstar.entity;

import javax.persistence.*;

@Entity
@Table(name="GSS_STAFF")
public class GSSStaff {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="GSS_STAFF_SEQ")
    @SequenceGenerator(name="GSS_STAFF_SEQ", sequenceName="GSS_STAFF_SEQ", allocationSize=1)
    private int id;

    @Column(name="STAFF_CODE")
    private String staffCode;

    @Column(name="STAFF_NAME")
    private String staffName;

    @Column(name="status")
    private int status;

    @Column(name="token")
    private String token;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
