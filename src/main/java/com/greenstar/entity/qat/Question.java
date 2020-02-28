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
@Table(name="QAT_QUESTIONS")
public class Question {
    @Id
    @Column(name="ID")
    private int id;

    @Column(name="question")
    private String question;

    @Column(name="isCritical")
    private int isCritical;

    @Column(name="status")
    private int status;

    @Column(name="areaId")
    private int areaId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getIsCritical() {
        return isCritical;
    }

    public void setIsCritical(int isCritical) {
        this.isCritical = isCritical;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }
}
