package com.greenstar.entity.qat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Syed Muhammad Hassan
 * 6th February, 2020
 */

@Entity
@Table(name="QAT_AREA")
public class Area {

    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "area")
    private String area;

    @Column(name = "status")
    private int status;

    public Area() {
    }

    public Area(int id, String area, int status) {
        this.id = id;
        this.area = area;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}