package com.greenstar.entity.qtv;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Syed Muhammad Hassan
 * 24/06/2019
 */

@Entity
@Table(name="HS_PROVIDER_CHO")
public class ProviderCHO {
    @Id
    @Column(name="PROVIDER_CODE")
    private String providerCode;

    @Column(name="TERRITORY_CODE")
    private String territoryCode;

    public String getProviderCode() {
        return providerCode;
    }

    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }

    public String getTerritoryCode() {
        return territoryCode;
    }

    public void setTerritoryCode(String territoryCode) {
        this.territoryCode = territoryCode;
    }
}
