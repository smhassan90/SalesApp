package com.greenstar.entity.sale.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Syed Muhammad Hassan
 * 29th April, 2021
 */
@Entity
@Table(name="BASE_DISTRICT_MASTER")
public class DistrictMaster {
    @Id
    @Column(name="ID")
    private String ID;
    @Column(name="NAME")
    private String NAME;
    @Column(name="DONOR")
    private String DONOR;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getDONOR() {
        return DONOR;
    }

    public void setDONOR(String DONOR) {
        this.DONOR = DONOR;
    }
}
