import * as React from "react";
import axios from "axios";
import { useParams, useNavigate } from "react-router-dom";
import styled from "styled-components";

const AddQuestion = () => {
  const navigate = useNavigate();
  const { id } = useParams();
  const { nQts } = useParams();
  const [questions, setQuestions] = React.useState([]);

  React.useEffect(() => {
    const initialQuestions = Array.from(
      { length: parseInt(nQts) },
      () => ({
        question: "",
        quiz: {
          quizId: id,
        },
        option1: "",
        option2: "",
        option3: "",
        option4: "",
        answer: 0,
      })
    );
    setQuestions(initialQuestions);
  }, [nQts, id]);

  const handleQuestionChange = (index, event) => {
    const { name, value } = event.target;
    console.log(JSON.stringify(name + " " + value));
    const updatedQuestions = [...questions];
    if (name === "answer") {
      updatedQuestions[index][name] = parseInt(value);
    } else {
      updatedQuestions[index][name] = value;
    }
    setQuestions(updatedQuestions);
  };

  const handleSubmit = async () => {
    console.log(JSON.stringify(questions));
    try {
      const response = await axios.post(
        "http://localhost:8084/quizzes/addQuestions",
        questions
      );
      if (response.status === 201) {
        alert("questions added successfully");
        navigate(`/SearchQuizzes`);
      }
    } catch (error) {
      throw new Error("while adding questions : " + error.message);
    }
  };

  return (
    <Container>
      <QuizId>Quiz Id : {id}</QuizId>
      <Header>Add Questions</Header>
      <QuestionsContainer>
        {questions.map((question, index) => (
          <Question key={index}>
            <QuestionTitle>Question {index + 1}</QuestionTitle>
            <QuestionInput
              type="text"
              name="question"
              value={question.question}
              onChange={(e) => handleQuestionChange(index, e)}
              placeholder="Enter question"
            />
            <OptionInput
              type="text"
              name="option1"
              value={question.option1}
              onChange={(e) => handleQuestionChange(index, e)}
              placeholder="Option 1"
            />
            <OptionInput
              type="text"
              name="option2"
              value={question.option2}
              onChange={(e) => handleQuestionChange(index, e)}
              placeholder="Option 2"
            />
            <OptionInput
              type="text"
              name="option3"
              value={question.option3}
              onChange={(e) => handleQuestionChange(index, e)}
              placeholder="Option 3"
            />
            <OptionInput
              type="text"
              name="option4"
              value={question.option4}
              onChange={(e) => handleQuestionChange(index, e)}
              placeholder="Option 4"
            />
            <AnswerInput
              type="number"
              name="answer"
              value={question.answer}
              onChange={(e) => handleQuestionChange(index, e)}
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

const QuizId = styled.h1`
  margin-bottom: 1rem;
`;

const Header = styled.h2`
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

const QuestionInput = styled.input`
  width: 100%;
  padding: 0.5rem;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 1rem;
  margin-bottom: 1rem;
  &:focus {
    outline: none;
    border-color: #6b46c1;
  }
`;

const OptionInput = styled.input`
  width: 100%;
  padding: 0.5rem;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 1rem;
  margin-bottom: 0.5rem;
  &:focus {
    outline: none;
    border-color: #6b46c1;
  }
`;

const AnswerInput = styled.input`
  width: 100%;
  padding: 0.5rem;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 1rem;
  margin-bottom: 1rem;
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

export default AddQuestion;