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
@Table(name="BASE_EMP_ZONE_MAPPING")
public class EmployeeZoneMapping {
    @Id
    @Column(name="POSITION_ID")
    private String POSITION_ID;
    @Column(name="ZONE_ID")
    private int ZONE_ID;

    public String getPOSITION_ID() {
        return POSITION_ID;
    }

    public void setPOSITION_ID(String POSITION_ID) {
        this.POSITION_ID = POSITION_ID;
    }

    public int getZONE_ID() {
        return ZONE_ID;
    }

    public void setZONE_ID(int ZONE_ID) {
        this.ZONE_ID = ZONE_ID;
    }
}
