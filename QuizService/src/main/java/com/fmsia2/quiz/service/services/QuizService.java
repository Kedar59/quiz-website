package com.fmsia2.quiz.service.services;

import com.fmsia2.quiz.service.entities.Quiz;

import java.util.List;

public interface QuizService {
    Quiz saveQuiz(Quiz quiz);
    List<Quiz> getAllQuizzes();
    Quiz getQuiz(String quizId);
    void updateNumberOfQuestions(String quizId,int updatedNumber);
}
