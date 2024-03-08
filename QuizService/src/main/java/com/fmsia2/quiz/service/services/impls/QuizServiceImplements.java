package com.fmsia2.quiz.service.services.impls;

import com.fmsia2.quiz.service.services.QuizService;
import com.fmsia2.quiz.service.entities.Quiz;
import com.fmsia2.quiz.service.exceptions.ResourceNotFoundException;
import com.fmsia2.quiz.service.repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class QuizServiceImplements implements QuizService {
    @Autowired
    private QuizRepository quizRepository;

    @Override
    public Quiz saveQuiz(Quiz quiz){
        String randomQuizId = UUID.randomUUID().toString();
        quiz.setQuizId(randomQuizId);
        return quizRepository.save(quiz);
    }

    @Override
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    @Override
    public Quiz getQuiz(String quizId) {
        String qid = new String(quizId);
        System.out.println(qid);
        return quizRepository.findById(qid).orElseThrow(()-> new ResourceNotFoundException("Quiz with quizId : "+qid+" is not found on the server"));
    }
    @Override
    public void updateNumberOfQuestions(String quizId,int updatedNumber){
        String qid = new String(quizId);
        Quiz quiz1 = getQuiz(qid);
        quiz1.setNumberOfQuestions(updatedNumber);
        quizRepository.save(quiz1);
    }
}
