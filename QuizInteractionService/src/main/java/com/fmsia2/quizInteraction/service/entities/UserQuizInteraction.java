package com.fmsia2.quizInteraction.service.entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="userquizinteraction")
public class UserQuizInteraction {
    @Id
    private String interactionId;
    private Boolean attempted;
    private Boolean checked;
    private int totalQts;
    private int noOfCorrectAnswers;
    private String userId;
    private String quizId;
    private List<UserAnswer> userAnswers;

    public UserQuizInteraction(String interactionId, Boolean attempted, Boolean checked, int totalQts, int noOfCorrectAnswers, String userId, String quizId, List<UserAnswer> userAnswers) {
        this.interactionId = interactionId;
        this.attempted = attempted;
        this.checked = checked;
        this.totalQts = totalQts;
        this.noOfCorrectAnswers = noOfCorrectAnswers;
        this.userId = userId;
        this.quizId = quizId;
        this.userAnswers = userAnswers;
    }

    public UserQuizInteraction(String userId, String quizId,int totalQts,int noOfCorrectAnswers,Boolean attempted,Boolean checked) {
        this.userId = userId;
        this.quizId = quizId;
        this.totalQts = totalQts;
        this.noOfCorrectAnswers = noOfCorrectAnswers;
        this.attempted = attempted;
        this.checked = checked;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public int getTotalQts() {
        return totalQts;
    }

    public void setTotalQts(int totalQts) {
        this.totalQts = totalQts;
    }

    public int getNoOfCorrectAnswers() {
        return noOfCorrectAnswers;
    }

    public void setNoOfCorrectAnswers(int noOfCorrectAnswers) {
        this.noOfCorrectAnswers = noOfCorrectAnswers;
    }
    public Boolean getAttempted() {
        return attempted;
    }

    public void setAttempted(Boolean attempted) {
        this.attempted = attempted;
    }

    public UserQuizInteraction(){}
    public String getInteractionId() {
        return interactionId;
    }
    public void setInteractionId(String interactionId) {
        this.interactionId = interactionId;
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
    public List<UserAnswer> getUserAnswers() {
        return userAnswers;
    }
    public void setUserAnswers(List<UserAnswer> userAnswers) {
        this.userAnswers = userAnswers;
    }
    
}
