package com.greenstar.entity.eagle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author Syed Muhammad Hassan
 * 9/01/2023
 */

@Entity
@Table(name="EAGLE_SCREENING_TEST")
public class ScreeningTest {
    @Id
    @Column(name = "ID")
    private long id;
    @Column(name = "formId")
    private long formId;
    @Column(name = "areaId")
    private int areaId;
    @Column(name = "testId")
    private long testId;
    @Column(name = "testOutcome")
    private String testOutcome;
    @Column(name = "testDetail")
    private String testDetail;

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

    public long getTestId() {
        return testId;
    }

    public void setTestId(long testId) {
        this.testId = testId;
    }

    public String getTestOutcome() {
        return testOutcome;
    }

    public void setTestOutcome(String testOutcome) {
        this.testOutcome = testOutcome;
    }

    public String getTestDetail() {
        return testDetail;
    }

    public void setTestDetail(String testDetail) {
        this.testDetail = testDetail;
    }
}
