package com.greenstar.entity.eagle;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Syed Muhammad Hassan
 * 1/12/2022
 */

@Entity
@Table(name="EAGLE_QUESTIONS")
public class Questions {
    @Id
    @Column(name="ID")
    private long id;
    @Column(name="DETAIL")
    private String detail;
    @Column(name="STATUS")
    private int status;
    @Column(name="TYPE")
    private int type;
    @Column(name="POINTS")
    private int points;
    @Column(name="AREAID")
    private int areaId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }
}
