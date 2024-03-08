package com.fmsia2.user.service.controllers;

import com.fmsia2.user.service.projections.QuizForReview;
import com.fmsia2.user.service.projections.QuizWithQuestions;
import com.fmsia2.user.service.entities.User;
import com.fmsia2.user.service.services.UserService;
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
    @GetMapping("/getUserWithQuizList/{userId}")
    public ResponseEntity<User> getSingleUserWithQuizzes(@PathVariable String userId){
        return ResponseEntity.ok(userService.getUserWithQuizzes(userId));
    }
    @GetMapping("/allUsers")
    public ResponseEntity<List<User>> listAllUsers(){
        List<User> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }

    @GetMapping("/takeQuiz/{quizId}")
    public ResponseEntity<QuizWithQuestions> getQuizToAttempt(@PathVariable String quizId){
        return ResponseEntity.ok(userService.getQuizForAttempt(quizId));
    }

    @GetMapping("/reviewQuiz/{userId}/{quizId}")
    public ResponseEntity<QuizForReview> getQuizForReview(@PathVariable String userId,@PathVariable String quizId){
        return ResponseEntity.ok(userService.getQuizForReview(userId,quizId));
    }
}
