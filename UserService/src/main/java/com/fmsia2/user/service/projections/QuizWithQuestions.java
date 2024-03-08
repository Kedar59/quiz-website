package com.fmsia2.user.service.projections;

import com.fmsia2.user.service.projections.OnlyQuestion;
import com.fmsia2.user.service.projections.Quiz;
import com.fmsia2.user.service.repositories.UserRepository;

import java.util.List;

public class QuizWithQuestions {
    private Quiz quiz;
    private List<OnlyQuestion> listQuestions;

    public QuizWithQuestions(){}
    public QuizWithQuestions(Quiz quiz, List<OnlyQuestion> listQuestions) {
        this.quiz = quiz;
        this.listQuestions = listQuestions;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public List<OnlyQuestion> getListQuestions() {
        return listQuestions;
    }

    public void setListQuestions(List<OnlyQuestion> listQuestions) {
        this.listQuestions = listQuestions;
    }
}
