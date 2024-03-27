import * as React from "react";
import axios from "axios";
import { useParams } from "react-router-dom";
import { useSelector } from "react-redux";
function Review() {
  const user1 = useSelector((state) => state.user);
  const { quizId } = useParams();
  const [quizReview, setQuizReview] = React.useState({});

  React.useEffect(() => {
    const fetchUser = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8084/users/reviewQuiz/${user1}/${quizId}`
        );
        setQuizReview(response.data);
        console.log(response.data);
      } catch (error) {
        console.error("Error fetching quizzes:", error);
      }
    };
    fetchUser();
  }, [quizId]);
  return (
    <>
      {/* <p>{JSON.stringify(quizReview)}</p> */}
      {quizReview.interaction &&
        quizReview.interaction.quiz &&
        quizReview.interaction.quiz.numberOfQuestions && 
        (
          <>
            <h1> Quiz Title : {quizReview.interaction.quiz.title} </h1>
            <h1>
              Score : {quizReview.interaction.noOfCorrectAnswers} /{" "}
              {quizReview.interaction.quiz.numberOfQuestions}
            </h1>
          </>
        )}
        {Array.isArray(quizReview.listQts) &&
        quizReview.listQts.length > 0 &&
        quizReview.listQts.map((qts,index)=>(
            <div key={qts.question.questionId}>
                <h5>question {index + 1} : {qts.question.question}</h5>
                <p>option1 : {qts.question.option1}</p>
                <p>option2 : {qts.question.option2}</p>
                <p>option3 : {qts.question.option3}</p>
                <p>option4 : {qts.question.option4}</p>
                <p>correct answer : {qts.question.answer}</p>
                <p>user answer : {qts.userAnswer}</p>
                {/* add coloring of correct and wrong options */}
            </div>
        ))}
    </>
  );
}
export default Review;
