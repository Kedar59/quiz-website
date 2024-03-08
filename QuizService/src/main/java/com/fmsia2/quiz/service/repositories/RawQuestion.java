package com.fmsia2.quiz.service.repositories;

public interface RawQuestion {
    String getQuestionId();
    String getQuestion();
    String getOption1();
    String getOption2();
    String getOption3();
    String getOption4();
    int getAnswer();
}
