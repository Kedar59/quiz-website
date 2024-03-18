package com.fmsia2.user.service.controllers;

import com.fmsia2.user.service.projections.QuizForReview;
import com.fmsia2.user.service.projections.QuizWithQuestions;
import com.fmsia2.user.service.entities.User;
import com.fmsia2.user.service.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    private Logger logger = LoggerFactory.getLogger(UserController.class);
    // create user
    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }
    @GetMapping("/profile/{userId}")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId){
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }
    // api calls interaction and quiz service
//    @CircuitBreaker(name="getUserInteractionOrQuizFallback", fallbackMethod = "getUserInteractionOrQuizFallback")
    int retryCount = 1;
    @GetMapping("/getUserWithQuizList/{userId}")
    @Retry(name="getUserInteractionOrQuizFallback", fallbackMethod = "getUserInteractionOrQuizFallback")
    public ResponseEntity<User> getSingleUserWithQuizzes(@PathVariable String userId){
        logger.info("Retry count: {}", retryCount);
        retryCount++;
        return ResponseEntity.ok(userService.getUserWithQuizzes(userId));
    }
    public ResponseEntity<User> getUserInteractionOrQuizFallback(String userId,Exception ex){
        logger.info("get user interaction Fallback method executed : "+ex.getMessage());
        User user = userService.getUser(userId);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<User>> listAllUsers(){
        List<User> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }
    // api calls take quiz service will add a call to create interaction
//    @CircuitBreaker(name="quizService",fallbackMethod = "quizServiceFallback")
    @GetMapping("/takeQuiz/{quizId}")
    @Retry(name="quizService",fallbackMethod = "quizServiceFallback")
    public ResponseEntity<QuizWithQuestions> getQuizToAttempt(@PathVariable String quizId){
        logger.info("Retry count: {}", retryCount);
        retryCount++;
        return ResponseEntity.ok(userService.getQuizForAttempt(quizId));
    }
    public ResponseEntity<QuizWithQuestions> quizServiceFallback(String quizId,Exception ex){
        logger.info("quiz service Fallback method executed : "+ex.getMessage());
        return new ResponseEntity<>(new QuizWithQuestions(),HttpStatus.OK);
    }

    // api calls interaction and quiz service
//    @CircuitBreaker(name="getQuizForReviewFailed",fallbackMethod = "getQuizForReviewFailedFallback")
    @GetMapping("/reviewQuiz/{userId}/{quizId}")
    @Retry(name="getQuizForReviewFailed",fallbackMethod = "getQuizForReviewFailedFallback")
    public ResponseEntity<QuizForReview> getQuizForReview(@PathVariable String userId,@PathVariable String quizId){
        logger.info("Retry count: {}", retryCount);
        retryCount++;
        return ResponseEntity.ok(userService.getQuizForReview(userId,quizId));
    }
    public ResponseEntity<QuizForReview> getQuizForReviewFailedFallback(String userId,String quizId,Exception ex){
        logger.info("quiz for review Fallback method executed : "+ex.getMessage());
        return new ResponseEntity<>(new QuizForReview(),HttpStatus.OK);
    }

}
