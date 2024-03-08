package com.fmsia2.quizInteraction.service.entities;

public class UserAnswer {
    private String questionId;
    private int answer;
    
    public UserAnswer(String questionId, int answer) {
        this.questionId = questionId;
        this.answer = answer;
    }
    public UserAnswer(){}
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
