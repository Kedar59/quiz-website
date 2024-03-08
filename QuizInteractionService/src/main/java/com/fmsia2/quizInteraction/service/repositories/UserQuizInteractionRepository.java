package com.fmsia2.quizInteraction.service.repositories;

import java.util.List;

import com.fmsia2.quizInteraction.service.entities.UserAnswer;
import com.fmsia2.quizInteraction.service.projections.Interaction;
import com.fmsia2.quizInteraction.service.projections.userAnswersObj;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.fmsia2.quizInteraction.service.entities.UserQuizInteraction;

public interface UserQuizInteractionRepository extends MongoRepository<UserQuizInteraction,String>{
    List<UserQuizInteraction> findByUserId(String userId);
    List<UserQuizInteraction> findByQuizId(String quizId);

    @Query("{ 'userId' : ?0, 'quizId' : ?1 }")
    UserQuizInteraction findByUserIdAndQuizId(String userId, String quizId);

    @Query(value = "{ 'userId' : ?0 }", fields = "{ 'userId' : 1, 'quizId' : 1, 'attempted' : 1, 'checked' : 1, 'noOfCorrectAnswers' : 1 }")
    List<Interaction> findByUserIdWithSelectiveFields(String userId);
    @Query(value = "{ 'userId' : ?0 , 'quizId' : ?1 }", fields = "{ 'userId' : 1, 'quizId' : 1, 'attempted' : 1, 'checked' : 1, 'noOfCorrectAnswers' : 1 }")
    Interaction getUserQuizInteraction(String userId,String quizId);
    @Query(value = "{ 'userId' : ?0 , 'quizId' : ?1 }" , fields = "{ 'userAnswers' : 1 }")
    userAnswersObj getUserAnswersForReview(String userId, String quizId);
}
