package com.fmsia2.quiz.service.services.impls;

import com.fmsia2.quiz.service.entities.Question;
import com.fmsia2.quiz.service.exceptions.ResourceNotFoundException;
import com.fmsia2.quiz.service.repositories.OnlyQuestion;
import com.fmsia2.quiz.service.repositories.QtsAnswer;
import com.fmsia2.quiz.service.repositories.QuestionRepository;
import com.fmsia2.quiz.service.repositories.RawQuestion;
import com.fmsia2.quiz.service.services.QuestionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class QuestionServiceImplements implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Override
    public List<RawQuestion> findQuestionsForReview(String quizId){
        return questionRepository.findQuestionsForReview(quizId);
    }
    @Override
    public Question saveQuestion(Question question) {
        String randomQuestionId = UUID.randomUUID().toString();
        question.setQuestionId(randomQuestionId);
        return questionRepository.save(question);
    }
    @Override
    public List<Question> getQuestionsByQuizIdWithoutAnswer(String quizId){
        return questionRepository.findQuestionsByQuizIdWithoutAnswer(quizId);
    }

    public List<Question> getQuestionsByQuizId(String quizId){
        return questionRepository.findQuestionsByQuizId(quizId);
    }

    @Override
    public Question getQuestion(String questionId) {
        String qtsid = new String(questionId);
        return questionRepository.findById(qtsid).orElseThrow(()-> new ResourceNotFoundException("Quiz with quizId : "+qtsid+" is not found on the server"));
    }

    @Override
    public List<QtsAnswer> getAnswersByQuizId(String quizId){
        return questionRepository.findAnswersByQuizId(quizId);
    }

    @Override
    public List<OnlyQuestion> getQuestionsForAttempt(String quizId){ return questionRepository.findQuestionsForAttempt(quizId); }
}
