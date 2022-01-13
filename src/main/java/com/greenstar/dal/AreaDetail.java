package com.greenstar.dal;

import com.greenstar.entity.qat.Question;

import java.util.List;

public class AreaDetail {
    String heading;
    List<Question> questions;

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
