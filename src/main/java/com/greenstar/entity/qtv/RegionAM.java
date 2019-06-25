package com.greenstar.entity.qtv;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Syed Muhammad Hassan
 * 24/06/2019
 */

@Entity
@Table(name="HS_REGION_AM")
public class RegionAM implements Serializable {
    @Id
    @Column(name="REGION")
    private String providerCode;

    @Id
    @Column(name="AM_CODE")
    private String AMCode;

    public String getProviderCode() {
        return providerCode;
    }

    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }

    public String getAMCode() {
        return AMCode;
    }

    public void setAMCode(String AMCode) {
        this.AMCode = AMCode;
    }
}
