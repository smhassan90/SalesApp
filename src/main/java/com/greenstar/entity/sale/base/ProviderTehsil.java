package com.greenstar.entity.sale.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Syed Muhammad Hassan
 * 24th May, 2021
 */

@Entity
@Table(name="BASE_PROVIDER_TEHSIL")
public class ProviderTehsil {
    @Id
    @Column(name="PROVIDERCODE")
    private int PROVIDERCODE;
    @Column(name="TEHSIL_ID")
    private int TEHSIL_ID;

    public int getPROVIDERCODE() {
        return PROVIDERCODE;
    }

    public void setPROVIDERCODE(int PROVIDERCODE) {
        this.PROVIDERCODE = PROVIDERCODE;
    }

    public int getTEHSIL_ID() {
        return TEHSIL_ID;
    }

    public void setTEHSIL_ID(int TEHSIL_ID) {
        this.TEHSIL_ID = TEHSIL_ID;
    }
}
