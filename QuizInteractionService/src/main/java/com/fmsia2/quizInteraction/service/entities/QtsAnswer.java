package com.fmsia2.quizInteraction.service.entities;

public class QtsAnswer {
    private String questionId;
    private int answer;

    public QtsAnswer(){}

    public QtsAnswer(String questionId, int answer) {
        this.questionId = questionId;
        this.answer = answer;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}
