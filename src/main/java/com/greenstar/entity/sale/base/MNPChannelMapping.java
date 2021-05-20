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
@Table(name="BASE_MNP_CHANNEL_MAPPING")
public class MNPChannelMapping {
    @Id
    @Column(name="CLASS_CODE")
    private String CLASS_CODE;
    @Column(name="CHANNEL")
    private String CHANNEL;

    public String getCLASS_CODE() {
        return CLASS_CODE;
    }

    public void setCLASS_CODE(String CLASS_CODE) {
        this.CLASS_CODE = CLASS_CODE;
    }

    public String getCHANNEL() {
        return CHANNEL;
    }

    public void setCHANNEL(String CHANNEL) {
        this.CHANNEL = CHANNEL;
    }
}
