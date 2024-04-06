import * as React from "react";
import axios from "axios";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";

const CreateQuiz = () => {
  const navigate = useNavigate();
  const user1 = useSelector((state) => state.user);
  const [title, setTitle] = React.useState("");
  const [numberOfQuestions, setNumberOfQuestions] = React.useState(0);
  const [user] = React.useState({ userId: user1 });

  const handleSubmit = async (event) => {
    const payload = {
      title: title,
      numberOfQuestions: numberOfQuestions,
      user: user,
    };

    if (!payload.title) {
      alert("title is empty");
    } else if (!payload.numberOfQuestions) {
      alert("number of questions is empty");
    } else if (!payload.user) {
      alert("user not logged in");
    }

    console.log(payload);

    try {
      const response = await axios.post(
        "http://localhost:8084/quizzes/createQuiz",
        payload
      );
      if (response.status === 201) {
        const quiz = response.data;
        console.log(JSON.stringify(quiz));
        navigate(`/addQuestion/${quiz.quizId}/${quiz.numberOfQuestion}`);
      } else {
        alert("some error occurred: " + response.status);
      }
    } catch (error) {
      let errorMessage = "Registration failed";
      try {
        const responseData = JSON.parse(error.request.response);
        if (responseData && responseData.error) {
          errorMessage = responseData.error;
        }
      } catch (parseError) {
        console.error("Error parsing response:", parseError);
      }
      console.log(errorMessage);
      alert(errorMessage);
    }
  };

  return (
    <Container>
      <Form>
        <Label>
          Title:
          <Input
            value={title}
            onChange={(e) => setTitle(e.target.value)}
          />
        </Label>
        <Label>
          Number Of Questions:
          <Input
            value={numberOfQuestions}
            onChange={(e) => {
              setNumberOfQuestions(parseInt(e.target.value));
            }}
            type="number"
            min={1}
          />
        </Label>
        <Button onClick={() => handleSubmit()}>Create Quiz</Button>
      </Form>
    </Container>
  );
};

const Container = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f5f5f5;
`;

const Form = styled.div`
  background-color: white;
  padding: 2rem;
  border-radius: 8px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  display: flex;
  flex-direction: column;
  gap: 1rem;
`;

const Label = styled.label`
  display: flex;
  flex-direction: column;
  font-size: 1rem;
  font-weight: bold;
`;

const Input = styled.input`
  padding: 0.5rem;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 1rem;
  &:focus {
    outline: none;
    border-color: #6b46c1;
  }
`;

const Button = styled.button`
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

export default CreateQuiz;