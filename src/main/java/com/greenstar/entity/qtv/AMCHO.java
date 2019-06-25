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
@Table(name="HS_AM_CHO")
public class AMCHO {
    @Column(name="AM")
    private String AM;

    @Id
    @Column(name="CHO")
    private String CHO;

    public String getAM() {
        return AM;
    }

    public void setAM(String AM) {
        this.AM = AM;
    }

    public String getCHO() {
        return CHO;
    }

    public void setCHO(String CHO) {
        this.CHO = CHO;
    }
}
