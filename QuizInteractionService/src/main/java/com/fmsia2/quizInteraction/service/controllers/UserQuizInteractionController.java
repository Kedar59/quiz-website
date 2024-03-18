package com.fmsia2.quizInteraction.service.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fmsia2.quizInteraction.service.entities.QtsAnswer;
import com.fmsia2.quizInteraction.service.entities.UserAnswer;
import com.fmsia2.quizInteraction.service.projections.Interaction;
import com.fmsia2.quizInteraction.service.projections.userAnswersObj;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fmsia2.quizInteraction.service.entities.UserQuizInteraction;
import com.fmsia2.quizInteraction.service.services.UserQuizInteractionService;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/interactions")
public class UserQuizInteractionController {

    @Autowired
    private UserQuizInteractionService interactionService;

    @Autowired
    private RestTemplate restTemplate;
    private Logger logger = LoggerFactory.getLogger(UserQuizInteractionController.class);

    @PostMapping("/registerForQuiz")
    public ResponseEntity<String> registerForQuiz(@RequestBody UserQuizInteraction interaction){
        // once we connect all the services 
        // add a check by taking users current time stamp and make sure it's less than quizzes starting time;
        // then do the create interaction operation given below else return an error message
        interactionService.saveInteraction(interaction);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered for quiz");
    } 

    @PostMapping("/getInteraction")
    public ResponseEntity<UserQuizInteraction> getbyUserAndQuizId(@RequestBody UserQuizInteraction interaction){
        UserQuizInteraction interaction1 = interactionService.getInteraction(interaction.getUserId(), interaction.getQuizId());
        return ResponseEntity.status(HttpStatus.FOUND).body(interaction1); 
    }
    @GetMapping("/getUserInteractions/{userId}")
    public ResponseEntity<List<Interaction>> getInteractionsByUserId(@PathVariable String userId){
        return ResponseEntity.status(HttpStatus.FOUND).body(interactionService.getAllInteractionsByUserId(userId));
    }

    @GetMapping("/getUserQuizInteractions/{userId}/{quizId}")
    public ResponseEntity<Interaction> getUserQuizInteraction(@PathVariable String userId,@PathVariable String quizId){
        return ResponseEntity.status(HttpStatus.FOUND).body(interactionService.getUserQuizInteraction(userId,quizId));
    }
    @GetMapping("/getUsersForQuiz/{quizId}")
    public ResponseEntity<List<UserQuizInteraction>> getUsersForQuizId(@PathVariable String quizId){
        List<UserQuizInteraction> usersListForQuiz = interactionService.getbyQuizId(quizId);
        return ResponseEntity.status(HttpStatus.FOUND).body(usersListForQuiz);
    }

    @GetMapping("/getQuizzesForUser/{userId}")
    public ResponseEntity<List<UserQuizInteraction>> getQuizzesForUser(@PathVariable String userId){
        List<UserQuizInteraction> quizzesListForUser = interactionService.getbyUserId(userId);
        return ResponseEntity.status(HttpStatus.FOUND).body(quizzesListForUser);
    }
    @GetMapping("/getUserAnswersForReview/{userId}/{quizId}")
    public ResponseEntity<List<UserAnswer>> getUserAnswersForReview(@PathVariable String userId, @PathVariable String quizId){
        userAnswersObj useranswersobj = interactionService.getUserAnswersForReview(userId,quizId);
        return ResponseEntity.status(HttpStatus.FOUND).body(useranswersobj.getUserAnswers());
    }
    int retryCount = 1;
//    @CircuitBreaker(name="quizServiceFailed",fallbackMethod = "quizServiceFallback")
    @PostMapping("/submitAnswers")
    @Retry(name="quizServiceFailed",fallbackMethod = "quizServiceFallback")
    public ResponseEntity<UserQuizInteraction> saveAnswers(@RequestBody UserQuizInteraction interaction){
        // here also once we connect all the services we will have to add a check
        // to see if answer submission is before quiz End-time compare with submission time
        logger.info("Retry count: {}", retryCount);
        retryCount++;
        UserQuizInteraction interaction1 = interactionService.getInteraction(interaction.getUserId(), interaction.getQuizId());

        interaction1.setUserAnswers(interaction.getUserAnswers());
        interaction1.setAttempted(true);
        interaction1 = interactionService.saveInteraction(interaction1);
        int noOfCorrectQuestions = 0;
        // call all the question for this quiz from quiz api and store the correct answer in the
        // interaction1.getUserAnswers()[i].
        QtsAnswer[] listQtsAnswers = restTemplate.getForObject("http://localhost:8084/quizzes/getQuizAnswers/"+interaction1.getQuizId(),QtsAnswer[].class);

        Map<String, Integer> qtsAnswerMap = new HashMap<>();
        for (QtsAnswer qtsAnswer : listQtsAnswers) {
            qtsAnswerMap.put(qtsAnswer.getQuestionId(), qtsAnswer.getAnswer());
        }

        // Count the number of correct answers
        for (UserAnswer userAnswer : interaction.getUserAnswers()) {
            String questionId = userAnswer.getQuestionId();
            int userAnswerValue = userAnswer.getAnswer();

            // Check if the question is in the qtsAnswerMap and the user's answer is correct
            if (qtsAnswerMap.containsKey(questionId) && qtsAnswerMap.get(questionId) == userAnswerValue) {
                noOfCorrectQuestions++;
            }
        }
        interaction1.setNoOfCorrectAnswers(noOfCorrectQuestions);
        interaction1.setChecked(true);
        interaction1 = interactionService.saveInteraction(interaction1);

        return ResponseEntity.status(HttpStatus.FOUND).body(interaction1);
    }
    public ResponseEntity<UserQuizInteraction> quizServiceFallback(UserQuizInteraction interaction,Exception ex){
        logger.info("Quiz service fallback "+ex.getMessage());
        return new ResponseEntity<>(new UserQuizInteraction(),HttpStatus.SERVICE_UNAVAILABLE);
    }
    

}
