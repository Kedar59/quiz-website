package com.fmsia2.quizInteraction.service.projections;

public class Interaction {
    private String userId;
    private String quizId;
    private Boolean attempted;
    private Boolean checked;
    private int noOfCorrectAnswers;
    private Quiz quiz;

    public Interaction(){}
    public Interaction(String userId, String quizId, Boolean attempted, Boolean checked, int noOfCorrectAnswers, Quiz quiz) {
        this.userId = userId;
        this.quizId = quizId;
        this.attempted = attempted;
        this.checked = checked;
        this.noOfCorrectAnswers = noOfCorrectAnswers;
        this.quiz = quiz;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getQuizId() {
        return quizId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

    public Boolean getAttempted() {
        return attempted;
    }

    public void setAttempted(Boolean attempted) {
        this.attempted = attempted;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public int getNoOfCorrectAnswers() {
        return noOfCorrectAnswers;
    }

    public void setNoOfCorrectAnswers(int noOfCorrectAnswers) {
        this.noOfCorrectAnswers = noOfCorrectAnswers;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}
