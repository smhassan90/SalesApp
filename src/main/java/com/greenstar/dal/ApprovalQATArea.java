package com.greenstar.dal;

/**
 * @author Syed Muhammad Hassan
 * 21-APR-2020
 */
public class ApprovalQATArea {

    private long id;
    private long formId;
    private int areaId;
    private int totalIndicators;
    private int totalIndicatorsAchieved;
    private int totalCriticalIndicators;
    private int totalCriticalIndicatorsAchieved;
    private int totalNonCriticalIndicators;
    private int totalNonCriticalIndicatorsAchieved;
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
}
