package com.fmsia2.user.service.services;

import com.fmsia2.user.service.projections.QuizForReview;
import com.fmsia2.user.service.projections.QuizWithQuestions;
import com.fmsia2.user.service.entities.User;

import java.util.List;

public interface UserService {
    // user operations
    // create user
    User saveUser(User user);
    //get all users
    List<User> getAllUsers();

    // get a user for userId
    User getUser(String userId);

    User getUserWithQuizzes(String userId);
    QuizWithQuestions getQuizForAttempt(String quizId);
    QuizForReview getQuizForReview(String userId,String quizId);
}
