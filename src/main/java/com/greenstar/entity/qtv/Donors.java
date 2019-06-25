package com.greenstar.entity.qtv;

import javax.persistence.*;

/**
 * @author Syed Muhammad Hassan
 * 24/06/2019
 */

@Entity
@Table(name="HS_DONORS")
public class Donors {
    @Id
    @Column(name="CODE")
    private int code;

    @Column(name="NAME")
    private String name;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
