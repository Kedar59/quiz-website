package com.fmsia2.user.service.services.impls;

import com.fmsia2.user.service.entities.*;
import com.fmsia2.user.service.exceptions.ResourceNotFoundException;
import com.fmsia2.user.service.projections.*;
import com.fmsia2.user.service.repositories.UserRepository;
import com.fmsia2.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImplements implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(UserService.class);
    @Override
    public QuizForReview getQuizForReview(String userId, String quizId){
        ResponseEntity<Interaction> forEntity = restTemplate.getForEntity("http://INTERACTION-SERVICE/interactions/getUserQuizInteractions/"+userId+"/"+quizId,Interaction.class);
        Interaction interaction = forEntity.getBody();
        Question[] listRawQts = restTemplate.getForObject("http://QUIZ-SERVICE/quizzes/getQuestionsForReview/"+quizId,Question[].class);
        List<Question> rawQuestionsList = Arrays.stream(listRawQts).toList();
        UserAnswer[] listUserAnswers = restTemplate.getForObject("http://INTERACTION-SERVICE/interactions/getUserAnswersForReview/"+userId+"/"+quizId,UserAnswer[].class);
        List<UserAnswer> userAnswersList = Arrays.stream(listUserAnswers).toList();
        logger.info("Printing list:");
        for (UserAnswer obj : userAnswersList) {
            logger.info(obj.toString());
        }
        Map<String, Integer> userAnswerMap = new HashMap<>();
        for (UserAnswer userAnswer : userAnswersList) {
            userAnswerMap.put(userAnswer.getQuestionId(), userAnswer.getAnswer());
        }

        List<QtsReview> listQts = rawQuestionsList.stream().map(qtsObj -> {
            QtsReview reviewQuestion = new QtsReview(qtsObj,userAnswerMap.get(qtsObj.getQuestionId()));
            return reviewQuestion;
        }).collect(Collectors.toList());
        return new QuizForReview(interaction,listQts);
    }
    @Override
    public QuizWithQuestions getQuizForAttempt(String quizId){
        ResponseEntity<Quiz> forEntity = restTemplate.getForEntity("http://QUIZ-SERVICE/quizzes/getQuiz/"+quizId,Quiz.class);
        Quiz quiz = forEntity.getBody();
        OnlyQuestion[] questionsList = restTemplate.getForObject("http://QUIZ-SERVICE/quizzes/getQuestionsForAttempt/"+quizId, OnlyQuestion[].class);
        List<OnlyQuestion> onlyQuestionsList = Arrays.stream(questionsList).toList();
        return new QuizWithQuestions(quiz,onlyQuestionsList);
    }
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    @Override
    public User getUserWithQuizzes(String userId){
        String uid = new String(userId);
        User user  = userRepository.findById(uid).orElseThrow(()-> new ResourceNotFoundException("User with given id : " + userId + " is not found on the server"));
        Interaction[] interactionsOfUsers = restTemplate.getForObject("http://INTERACTION-SERVICE/interactions/getUserInteractions/"+user.getUserId(),Interaction[].class);
        List<Interaction> interactions = Arrays.stream(interactionsOfUsers).toList();
        List<Interaction> interactionList = interactions.stream().map(interaction -> {
            ResponseEntity<Quiz> forEntity = restTemplate.getForEntity("http://QUIZ-SERVICE/quizzes/getQuiz/"+interaction.getQuizId(),Quiz.class);
            Quiz quiz = forEntity.getBody();
            interaction.setQuiz(quiz);
            return interaction;
        }).collect(Collectors.toList());
        user.setListOfInteractions(interactionList);
        return user;
    }
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        String uid = new String(userId);
        return userRepository.findById(uid).orElseThrow(()-> new ResourceNotFoundException("User with given id : " + userId + " is not found on the server"));
    }
}
