import * as React from "react";
import axios from "axios";
import { useParams, useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";
import styled from "styled-components";

const AttemptQuiz = () => {
  const navigate = useNavigate();
  const user1 = useSelector((state) => state.user);
  const { quizId } = useParams();
  const [quiz, setQuiz] = React.useState([]);
  const [listQuestions, setListQuestions] = React.useState([]);
  const [userAnswers, setUserAnswers] = React.useState([]);

  const handleSubmit = async () => {
    console.log(JSON.stringify(userAnswers));
    const payload = {
      userId: user1,
      quizId: quizId,
      userAnswers: userAnswers,
      attempted: false,
      checked: false,
    };
    console.log(payload);
    try {
      const response = await axios.post(
        "http://localhost:8084/interactions/submitAnswers",
        payload
      );
      if (response.status === 202) {
        alert("questions accepted");
        console.log(response.data);
        navigate("/profile");
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

  React.useEffect(() => {
    const initialUserAnswers = Array.from(
      { length: quiz.numberOfQuestions },
      () => ({
        questionId: "",
        answer: 0,
      })
    );
    setUserAnswers(initialUserAnswers);
  }, [quiz]);

  return (
    <Container>
      <Title>Quiz title : {quiz.title}</Title>
      <Details>Number of questions : {quiz.numberOfQuestions}</Details>
      <Header>Attempt quiz</Header>
      <QuestionsContainer>
        {listQuestions.map((question, index) => (
          <Question key={index}>
            <QuestionTitle>Question {index + 1}</QuestionTitle>
            <QuestionText>{question.question}</QuestionText>
            <OptionsContainer>
              <Option>{question.option1}</Option>
              <Option>{question.option2}</Option>
              <Option>{question.option3}</Option>
              <Option>{question.option4}</Option>
            </OptionsContainer>
            <AnswerInput
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
          </Question>
        ))}
      </QuestionsContainer>
      <SubmitButton onClick={handleSubmit}>Submit</SubmitButton>
    </Container>
  );
};

const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 2rem;
`;

const Title = styled.h1`
  margin-bottom: 1rem;
`;

const Details = styled.p`
  margin-bottom: 2rem;
`;

const Header = styled.h1`
  margin-bottom: 2rem;
`;

const QuestionsContainer = styled.div`
  display: flex;
  flex-direction: column;
  gap: 2rem;
  width: 100%;
  max-width: 800px;
`;

const Question = styled.div`
  background-color: #f5f5f5;
  padding: 1.5rem;
  border-radius: 8px;
`;

const QuestionTitle = styled.h3`
  margin-bottom: 1rem;
`;

const QuestionText = styled.p`
  margin-bottom: 1rem;
`;

const OptionsContainer = styled.div`
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
  margin-bottom: 1rem;
`;

const Option = styled.p`
  margin: 0;
`;

const AnswerInput = styled.input`
  padding: 0.5rem;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 1rem;
  &:focus {
    outline: none;
    border-color: #6b46c1;
  }
`;

const SubmitButton = styled.button`
  background-color: #6b46c1;
  color: white;
  padding: 0.75rem 1.5rem;
  border: none;
  border-radius: 4px;
  font-size: 1rem;
  cursor: pointer;
  &:hover {
    background-color: #512da8;
  }
`;

export default AttemptQuiz;