package com.fmsia2.quiz.service.controllers;

import com.fmsia2.quiz.service.entities.Question;
import com.fmsia2.quiz.service.repositories.OnlyQuestion;
import com.fmsia2.quiz.service.repositories.QtsAnswer;
import com.fmsia2.quiz.service.repositories.RawQuestion;
import com.fmsia2.quiz.service.services.QuestionService;
import com.fmsia2.quiz.service.entities.Quiz;
import com.fmsia2.quiz.service.services.QuizService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/quizzes")
public class QuizController {
    @Autowired
    private QuizService quizService;
    @Autowired
    private QuestionService questionService;
    private Logger logger = LoggerFactory.getLogger(QuizController.class);
    @PostMapping("/createQuiz")
    public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz quiz){
        Quiz quiz1 = quizService.saveQuiz(quiz);
        return ResponseEntity.status(HttpStatus.CREATED).body(quiz1);
    }

    @PostMapping("/addQuestions")
    public ResponseEntity<String> addQuestionsToQuiz(@RequestBody List<Question> questionsList) throws Exception{
        String quizId = questionsList.get(0).getQuiz().getQuizId();
        quizService.updateNumberOfQuestions(quizId, questionsList.size());
        List<Question> qtsList = new ArrayList<>();
        for(int i=0;i<questionsList.size();i++){
            Question qts1 = questionService.saveQuestion(questionsList.get(i));
            qtsList.add(qts1);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Questions added successfully");
    }

    @GetMapping("/getQuiz/{quizId}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable String quizId){
        Quiz quiz1 = quizService.getQuiz(quizId);
        return ResponseEntity.status(HttpStatus.FOUND).body(quiz1);
    }

    @GetMapping("/getQuestionsWithoutAnswer/{quizId}")
    public ResponseEntity<List<Question>> getQtsOfQuizWithoutAnswer(@PathVariable String quizId){
        List<Question> quizQuestions = questionService.getQuestionsByQuizIdWithoutAnswer(quizId);
        return ResponseEntity.status(HttpStatus.FOUND).body(quizQuestions);
    }

    @GetMapping("/getQuestions/{quizId}")
    public ResponseEntity<List<Question>> getQtsOfQuiz(@PathVariable String quizId){
        List<Question> quizQuestions = questionService.getQuestionsByQuizId(quizId);
        return ResponseEntity.status(HttpStatus.FOUND).body(quizQuestions);
    }

    @GetMapping("/getQuizAnswers/{quizId}")
    public ResponseEntity<List<QtsAnswer>> getQuizAnswers(@PathVariable String quizId){
        logger.info("Interaction service request with quizId :" + quizId );
        return ResponseEntity.status(HttpStatus.FOUND).body(questionService.getAnswersByQuizId(quizId));
    }

    @GetMapping("/getQuestionsForAttempt/{quizId}")
    public ResponseEntity<List<OnlyQuestion>> getQuestionsForAttempt(@PathVariable String quizId){
        return ResponseEntity.status(HttpStatus.FOUND).body(questionService.getQuestionsForAttempt(quizId));
    }
    @GetMapping("/getQuestionsForReview/{quizId}")
    public ResponseEntity<List<RawQuestion>> getQuestionsForReview(@PathVariable String quizId){
        return ResponseEntity.status(HttpStatus.FOUND).body(questionService.findQuestionsForReview(quizId));
    }
}
