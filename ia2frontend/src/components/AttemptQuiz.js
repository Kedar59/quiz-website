import * as React from "react";
import axios from "axios";
import { useParams, useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";

function AttemptQuiz() {
  const navigate = useNavigate();
  const user1 = useSelector((state) => state.user);
  const { quizId } = useParams();
  const [quiz, setQuiz] = React.useState([]);
  const [listQuestions, setListQuestions] = React.useState([]);
  const [userAnswers, setUserAnswers] = React.useState([]);
  const handleSubmit = async () => {
    console.log(JSON.stringify(userAnswers));
    const payload = {
        userId : user1,
        quizId : quizId,
        userAnswers : userAnswers,
        attempted : false,
        checked : false
    };
    console.log(payload);
    try {
      const response = await axios.post('http://localhost:8084/interactions/submitAnswers', payload);
      if(response.status === 202){
        alert("questions accepted");
        console.log(response.data);
        navigate('/profile');
      }
    } catch (error) {
        throw new Error("while adding questions : " + error.message);
    }
  };
  React.useEffect(() => {
    const fetchQuiz = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8084/users/takeQuiz/${quizId}`
        );
        setQuiz(response.data.quiz);
        setListQuestions(response.data.listQuestions);
      } catch (error) {
        console.error("Error fetching quizzes:", error);
      }
    };
    fetchQuiz();
    
  }, [quizId]);
  React.useEffect(()=>{
    const initialUserAnswers = Array.from(
        { length: quiz.numberOfQuestions },
        () => ({
          questionId: "",
          answer: 0,
        })
      );
      setUserAnswers(initialUserAnswers);
  },[quiz]);
  return (
    <>
        <p>{JSON.stringify(userAnswers)}</p>
      <h1>Quiz title : {quiz.title}</h1>
      <p>Number of questions : {quiz.numberOfQuestions}</p>
      <h1>Attempt quiz</h1>
      {listQuestions.map((question, index) => (
        <div key={index}>
          <h3>Question {index + 1}</h3>
          <h4>Question : {question.question}</h4>
          <br />
          <h4>option 1 : {question.option1}</h4>
          <br />
          <h4>option 2 : {question.option2}</h4>
          <br />
          <h4>option 3 : {question.option3}</h4>
          <br />
          <h4>option 4 : {question.option4}</h4>
          <br />
          <h4>User Answer</h4>
          <input
            type="number"
            name="answer"
            value={userAnswers[index]?.answer}
            onChange={(e) => {
                const { value } = e.target;
                const updatedUserAnswers = [...userAnswers];
                updatedUserAnswers[index]["answer"] = parseInt(value);
                updatedUserAnswers[index]["questionId"] = question.questionId;
                setUserAnswers(updatedUserAnswers);
                console.log(userAnswers);
            }}
            placeholder="Answer (1-4)"
            min="1"
            max="4"
          />
        </div>
      ))}
      <button onClick={handleSubmit}>Submit</button>
    </>
  );
}
export default AttemptQuiz;
