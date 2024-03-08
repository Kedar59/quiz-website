package com.fmsia2.user.service.projections;

import com.fmsia2.user.service.entities.User;
import jakarta.persistence.Column;

import java.time.ZonedDateTime;

public class Quiz {
    private String quizId;
    private String title;
    private int numberOfQuestions;
    private User user;
    private ZonedDateTime startTimestamp;
    private ZonedDateTime endTimestamp;
    private String timeZone;

    public Quiz(String quizId, String title, int numberOfQuestions, User user, ZonedDateTime startTimestamp, ZonedDateTime endTimestamp, String timeZone) {
        this.quizId = quizId;
        this.title = title;
        this.numberOfQuestions = numberOfQuestions;
        this.user = user;
        this.startTimestamp = startTimestamp;
        this.endTimestamp = endTimestamp;
        this.timeZone = timeZone;
    }
    public Quiz(){}

    public String getQuizId() {
        return quizId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ZonedDateTime getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(ZonedDateTime startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public ZonedDateTime getEndTimestamp() {
        return endTimestamp;
    }

    public void setEndTimestamp(ZonedDateTime endTimestamp) {
        this.endTimestamp = endTimestamp;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }
}
