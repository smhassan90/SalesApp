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
@Table(name="BASE_DIST_REGION_MAPPING")
public class DistrictRegionMapping {
    @Id
    @Column(name="DIST_ID")
    private String DIST_ID;
    @Column(name="REGION_ID")
    private String REGION_ID;

    public String getDIST_ID() {
        return DIST_ID;
    }

    public void setDIST_ID(String DIST_ID) {
        this.DIST_ID = DIST_ID;
    }

    public String getREGION_ID() {
        return REGION_ID;
    }

    public void setREGION_ID(String REGION_ID) {
        this.REGION_ID = REGION_ID;
    }
}
