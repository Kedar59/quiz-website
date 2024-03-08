package com.fmsia2.quiz.service.services;

import com.fmsia2.quiz.service.entities.Question;
import com.fmsia2.quiz.service.repositories.OnlyQuestion;
import com.fmsia2.quiz.service.repositories.QtsAnswer;
import com.fmsia2.quiz.service.repositories.RawQuestion;

import java.util.List;

public interface QuestionService {
    Question saveQuestion(Question question);

    List<Question> getQuestionsByQuizIdWithoutAnswer(String quizId);

    List<Question> getQuestionsByQuizId(String quizId);

    List<QtsAnswer> getAnswersByQuizId(String quizId);
    Question getQuestion(String questionId);

    List<OnlyQuestion> getQuestionsForAttempt(String quizId);
    List<RawQuestion> findQuestionsForReview(String quizId);
}
