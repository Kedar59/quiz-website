package com.fmsia2.quizInteraction.service.projections;

import com.fmsia2.quizInteraction.service.entities.UserAnswer;

import java.util.List;

public class userAnswersObj {
    private List<UserAnswer> userAnswers;

    public userAnswersObj(){}
    public List<UserAnswer> getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(List<UserAnswer> userAnswers) {
        this.userAnswers = userAnswers;
    }

    public userAnswersObj(List<UserAnswer> userAnswers) {
        this.userAnswers = userAnswers;
    }
}
