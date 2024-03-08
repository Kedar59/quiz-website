package com.fmsia2.quiz.service.repositories;

import com.fmsia2.quiz.service.entities.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Modifying;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;

public interface QuizRepository extends JpaRepository<Quiz,String> {
    // @Modifying
    // @Query("UPDATE Quiz q SET q.numberOfQuestions=:updatedNumber WHERE q.id=:qid")
    // int updateNoOfQts(@Param("qid") String qid,@Param("updatedNumber") int updatedNumber);
    // -----------DISCLAIMER------------
    // dont write queries to delete or update or insert in database in case of spring data jpa
    // spring data jpa want you to get all the rows you want to update then update the 
    // values you want to  in your code and then user QuizRepository.save(List<updated objects or Object>)
}
