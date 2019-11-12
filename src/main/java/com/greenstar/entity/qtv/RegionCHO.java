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
@Table(name="HS_REGION_CHO")
public class RegionCHO implements Serializable {
    @Column(name="REGION")
    private String region;

    @Id
    @Column(name="CHO_CODE")
    private String choCode;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getChoCode() {
        return choCode;
    }

    public void setChoCode(String choCode) {
        this.choCode = choCode;
    }
}
