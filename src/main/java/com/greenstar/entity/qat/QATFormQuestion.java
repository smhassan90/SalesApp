package com.greenstar.entity.qat;

import javax.persistence.*;

/**
 * @author Syed Muhammad Hassan
 * 4th April,2020
 */

@Entity
@Table(name="QAT_FormQuestion")
public class QATFormQuestion {
    @Id
   // @Column(name="ID")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="SQ_QATFormQuestion")
    @SequenceGenerator(name="SQ_QATFormQuestion", sequenceName="SQ_QATFormQuestion", allocationSize=1)
    private long id;

    @Column(name="questionId")
    private int questionId;

    @Column(name="areaId")
    private long areaId;

    @Column(name="answer")
    private int answer;

    @Column(name="formId")
    private long formId;

    public QATFormQuestion() {
    }

    public QATFormQuestion(int questionId, long areaId, int answer, long formId) {
        this.questionId = questionId;
        this.areaId = areaId;
        this.answer = answer;
        this.formId = formId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public long getAreaId() {
        return areaId;
    }

    public void setAreaId(long areaId) {
        this.areaId = areaId;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public long getFormId() {
        return formId;
    }

    public void setFormId(long formId) {
        this.formId = formId;
    }
}
