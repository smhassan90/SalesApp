package com.greenstar.entity.dtc;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Syed Muhammad Hassan
 * 18/09/2019
 */

@Entity
@Table(name="DTC_DISTRICT")
public class District {
    @Id
    @Column(name="DIST_CODE")
    private String distCode;
    @Column(name="DIST_NAME")
    private String distName;

    public String getDistCode() {
        return distCode;
    }

    public void setDistCode(String distCode) {
        this.distCode = distCode;
    }

    public String getDistName() {
        return distName;
    }

    public void setDistName(String distName) {
        this.distName = distName;
    }
}
