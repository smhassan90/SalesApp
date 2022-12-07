package com.greenstar.entity.eagle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

/**
 * @author Syed Muhammad Hassan
 * 6/12/2022
 */

@Entity
@Table(name="eagle_screening_areadetail")
public class ScreeningAreaDetail {
    @Id
    @Column(name="ID")
    private long id;
    @Column(name="formId")
    private long formId;
    @Column(name="areaId")
    private int areaId;
    @Column(name="totalIndicators")
    private int totalIndicators;
    @Column(name="totalIndicatorsAchieved")
    private int totalIndicatorsAchieved;
    @Column(name="totalCriticalIndicators")
    private int totalCriticalIndicators;
    @Column(name="totalCriticalIndicatorsAch")
    private int totalCriticalIndicatorsAchieved;
    @Column(name="totalPoints")
    private int totalPoints;
    @Column(name="comments")
    private String comments;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFormId() {
        return formId;
    }

    public void setFormId(long formId) {
        this.formId = formId;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public int getTotalIndicators() {
        return totalIndicators;
    }

    public void setTotalIndicators(int totalIndicators) {
        this.totalIndicators = totalIndicators;
    }

    public int getTotalIndicatorsAchieved() {
        return totalIndicatorsAchieved;
    }

    public void setTotalIndicatorsAchieved(int totalIndicatorsAchieved) {
        this.totalIndicatorsAchieved = totalIndicatorsAchieved;
    }

    public int getTotalCriticalIndicators() {
        return totalCriticalIndicators;
    }

    public void setTotalCriticalIndicators(int totalCriticalIndicators) {
        this.totalCriticalIndicators = totalCriticalIndicators;
    }

    public int getTotalCriticalIndicatorsAchieved() {
        return totalCriticalIndicatorsAchieved;
    }

    public void setTotalCriticalIndicatorsAchieved(int totalCriticalIndicatorsAchieved) {
        this.totalCriticalIndicatorsAchieved = totalCriticalIndicatorsAchieved;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
