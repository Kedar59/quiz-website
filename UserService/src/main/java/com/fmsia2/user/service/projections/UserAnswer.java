package com.fmsia2.user.service.projections;

public class UserAnswer {
    private String questionId;
    private int answer;

    @Override
    public String toString() {
        return "UserAnswer{" +
                "questionId='" + questionId + '\'' +
                ", answer=" + answer +
                '}';
    }

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
