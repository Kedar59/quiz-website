package com.fmsia2.quiz.service.repositories;

import com.fmsia2.quiz.service.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,String> {
    @Query("Select q from Question q JOIN q.quiz quiz WHERE quiz.quizId = :quizId")
    List<Question> findQuestionsByQuizId(@Param("quizId") String quizId);

    @Query("Select new Question(q.questionId,q.quiz,q.question,q.option1,q.option2,q.option3,q.option4) from Question q JOIN q.quiz quiz WHERE quiz.quizId = :quizId")
    List<Question> findQuestionsByQuizIdWithoutAnswer(@Param("quizId") String quizId);

    @Query("Select q.questionId AS questionId, q.answer AS answer FROM Question q JOIN q.quiz quiz where quiz.quizId = :quizId")
    List<QtsAnswer> findAnswersByQuizId(@Param("quizId") String quizId);

    @Query("Select q.questionId AS questionId, q.question AS question , q.option1 AS option1 , q.option2 AS option2 , q.option3 AS option3 , q.option4 AS option4  FROM Question q JOIN q.quiz quiz WHERE quiz.quizId = :quizId")
    List<OnlyQuestion> findQuestionsForAttempt(@Param("quizId") String quizId);

    @Query("Select q.questionId AS questionId, q.question AS question , q.option1 AS option1 , q.option2 AS option2 , q.option3 AS option3 , q.option4 AS option4 , q.answer AS answer FROM Question q JOIN q.quiz quiz WHERE quiz.quizId = :quizId")
    List<RawQuestion> findQuestionsForReview(@Param("quizId") String quizId);

}
