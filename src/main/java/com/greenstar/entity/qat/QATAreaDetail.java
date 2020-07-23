package com.greenstar.entity.qat;

import javax.persistence.*;

/**
 * @author Syed Muhammad Hassan
 * 4th April,2020
 */

@Entity
@Table(name="QAT_AreaDetail")
public class QATAreaDetail {

    @Id
 //   @Column(name="ID")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="SQ_QATAREADETAIL")
    @SequenceGenerator(name="SQ_QATAREADETAIL", sequenceName="SQ_QATAREADETAIL", allocationSize=1)
    private long id;

    @Column(name="totalIndicators")
    private int totalIndicators;

    @Column(name="totalIndicatorsAchieved")
    private int totalIndicatorsAchieved;

    @Column(name="totalCriticalIndicators")
    private int totalCriticalIndicators;

    @Column(name="totalCriticalIndicatorsAch")
    private int totalCriticalIndicatorsAchieved;

    @Column(name="totalNonCriticalIndicators")
    private int totalNonCriticalIndicators;

    @Column(name="totalNonCriticalIndicatorsAch")
    private int totalNonCriticalIndicatorsAchieved;

    @Column(name="comments")
    private String comments;

    @Column(name="formId")
    private long formId;

    @Column(name="areaId")
    private int areaId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getTotalNonCriticalIndicators() {
        return totalNonCriticalIndicators;
    }

    public void setTotalNonCriticalIndicators(int totalNonCriticalIndicators) {
        this.totalNonCriticalIndicators = totalNonCriticalIndicators;
    }

    public int getTotalNonCriticalIndicatorsAchieved() {
        return totalNonCriticalIndicatorsAchieved;
    }

    public void setTotalNonCriticalIndicatorsAchieved(int totalNonCriticalIndicatorsAchieved) {
        this.totalNonCriticalIndicatorsAchieved = totalNonCriticalIndicatorsAchieved;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
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
}
