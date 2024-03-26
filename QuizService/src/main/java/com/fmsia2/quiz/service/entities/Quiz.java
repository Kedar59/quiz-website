package com.fmsia2.quiz.service.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

// import java.sql.Timestamp;
import java.time.ZonedDateTime;

@Entity
@Table(name="Quizzes")
public class Quiz {
    @Id
    @Column(name="ID")
    private String quizId;
    @Column(name="TITLE")
    private String title;

    @Column(name="numberOfQuestions")
    private int numberOfQuestions;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "quiz_creator_user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

//    @Column(name = "startTime")
//    private ZonedDateTime startTimestamp;
//    @Column(name = "endTime")
//    private ZonedDateTime endTimestamp;
//    @Column(name = "timezone")
//    private String timeZone;

    // @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY)
    // private List<Question> questions;


    public Quiz(String quizId, String title, int numberOfQuestions, User user) {
        this.quizId = quizId;
        this.title = title;
        this.numberOfQuestions = numberOfQuestions;
        this.user = user;
//        this.startTimestamp = startTimestamp;
//        this.endTimestamp = endTimestamp;
//        this.timeZone = timeZone;
        // this.questions = questions;
    }

    public Quiz(){
    }

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

//    public ZonedDateTime getStartTimestamp() {
//        return startTimestamp;
//    }
//
//    public void setStartTimestamp(ZonedDateTime startTimestamp) {
//        this.startTimestamp = startTimestamp;
//    }
//
//    public ZonedDateTime getEndTimestamp() {
//        return endTimestamp;
//    }
//
//    public void setEndTimestamp(ZonedDateTime endTimestamp) {
//        this.endTimestamp = endTimestamp;
//    }
//
//    public String getRecordTimezone() {
//        return timeZone;
//    }
//
//    public void setRecordTimezone(String timeZone) {
//        this.timeZone = timeZone;
//    }
//
//    public String getTimeZone() {
//        return timeZone;
//    }
//
//    public void setTimeZone(String timeZone) {
//        this.timeZone = timeZone;
//    }

    // public List<Question> getQuestions() {
    //     return questions;
    // }

    // public void setQuestions(List<Question> questions) {
    //     this.questions = questions;
    // }
}
