package com.fmsia2.quizInteraction.service.services.impls;

import java.util.List;

import com.fmsia2.quizInteraction.service.entities.UserAnswer;
import com.fmsia2.quizInteraction.service.projections.Interaction;
import com.fmsia2.quizInteraction.service.projections.userAnswersObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fmsia2.quizInteraction.service.entities.UserQuizInteraction;
import com.fmsia2.quizInteraction.service.repositories.UserQuizInteractionRepository;
import com.fmsia2.quizInteraction.service.services.UserQuizInteractionService;


@Service
public class UserQuizInteractionServiceImplements implements UserQuizInteractionService{

    @Autowired
    private UserQuizInteractionRepository repository;
    @Override
    public userAnswersObj getUserAnswersForReview(String userId, String quizId){
        return repository.getUserAnswersForReview(userId,quizId);
    }
    @Override
    public List<Interaction> getAllInteractionsByUserId(String userId){
        return repository.findByUserIdWithSelectiveFields(userId);
    }
    @Override
    public Interaction getUserQuizInteraction(String userId,String quizId){
        return repository.getUserQuizInteraction(userId,quizId);
    }
    
    @Override
    public UserQuizInteraction getInteraction(String userId, String quizId) {
        return repository.findByUserIdAndQuizId(userId, quizId);
    }

    @Override
    public UserQuizInteraction updateAttempted(UserQuizInteraction interaction) {
        interaction.setAttempted(true);
        return repository.save(interaction);
    }

    @Override
    public UserQuizInteraction updateChecked(UserQuizInteraction interaction) {
        interaction.setChecked(true);
        return repository.save(interaction);
    }

    @Override
    public UserQuizInteraction saveInteraction(UserQuizInteraction interaction) {
        return repository.save(interaction);
    }

    @Override
    public List<UserQuizInteraction> getAll() {
        return repository.findAll();
    }

    @Override
    public List<UserQuizInteraction> getbyQuizId(String quizId) {
        return repository.findByQuizId(quizId);
    }

    @Override
    public List<UserQuizInteraction> getbyUserId(String userId) {
        return repository.findByUserId(userId);
    }

}
