import * as React from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";
import styled from "styled-components";

const SearchQuizzes = () => {
  const navigate = useNavigate();
  const user1 = useSelector((state) => state.user);
  const [quizzes, setQuizzes] = React.useState([]);

  React.useEffect(() => {
    const fetchQuizzes = async () => {
      try {
        const response = await axios.get(
          "http://localhost:8084/quizzes/getAllQuizzes"
        );
        setQuizzes(response.data);
        console.log(response.data);
      } catch (error) {
        console.error("Error fetching quizzes:", error);
      }
    };
    fetchQuizzes();
  }, []);

  return (
    <Container>

      <Table>
        <thead>
          <Row>
            <th>Quiz Title</th>
            <th>Quiz Creator</th>
            <th>Number of Questions</th>
            <th>Action</th>
          </Row>
        </thead>
        <tbody>
          {quizzes.map((quiz) => (
            <Row key={quiz.quizId}>
              <td>{quiz.title}</td>
              <td>{quiz.user.name}</td>
              <td>{quiz.numberOfQuestions}</td>
              <td>
                <button
                  onClick={async () => {
                    const payload = {
                      quizId: quiz.quizId,
                      userId: user1,
                      totalQts: parseInt(quiz.numberOfQuestions),
                      noOfCorrectAnswers: 0,
                    };
                    try {
                      const response = await axios.post(
                        "http://localhost:8084/interactions/registerForQuiz",
                        payload
                      );
                      if (response.status === 200) {
                        alert(response.data.message);
                      } else if (response.status === 202) {
                        alert(response.data.message);
                        navigate(`/attemptQuiz/${quiz.quizId}`);
                      } else if (response.status === 201) {
                        alert(response.data.message);
                        navigate(`/attemptQuiz/${quiz.quizId}`);
                      }
                    } catch (error) {
                      throw new Error("error while registering user " + error);
                    }
                  }}
                >
                  Attempt Quiz
                </button>
              </td>
            </Row>
          ))}
        </tbody>
      </Table>
    </Container>
  );
};

const Container = styled.div`
  display: flex;
  justify-content: center;
  padding: 2rem;
`;

const Table = styled.table`
  width: 100%;
  border-collapse: collapse;
  font-family: Arial, sans-serif;
`;

const Row = styled.tr`
  border-bottom: 1px solid #ddd;
  &:hover {
    background-color: #f5f5f5;
  }
`;

export default SearchQuizzes;