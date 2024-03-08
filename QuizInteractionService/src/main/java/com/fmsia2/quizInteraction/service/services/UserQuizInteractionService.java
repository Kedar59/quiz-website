package com.fmsia2.quizInteraction.service.services;

import java.util.List;

import com.fmsia2.quizInteraction.service.entities.UserAnswer;
import com.fmsia2.quizInteraction.service.entities.UserQuizInteraction;
import com.fmsia2.quizInteraction.service.projections.Interaction;
import com.fmsia2.quizInteraction.service.projections.userAnswersObj;

public interface UserQuizInteractionService {
    //create 
    UserQuizInteraction saveInteraction(UserQuizInteraction interaction);

    // get all
    List<UserQuizInteraction> getAll();

    // get by user Id
    List<UserQuizInteraction> getbyUserId(String userId);
    
    // get by quiz id
    List<UserQuizInteraction> getbyQuizId(String quizId); 

    UserQuizInteraction getInteraction(String userId,String quizId);

    UserQuizInteraction updateAttempted(UserQuizInteraction interaction);
    UserQuizInteraction updateChecked(UserQuizInteraction interaction);

    List<Interaction> getAllInteractionsByUserId(String userId);
    Interaction getUserQuizInteraction(String userId,String quizId);
    userAnswersObj getUserAnswersForReview(String userId, String quizId);

}
