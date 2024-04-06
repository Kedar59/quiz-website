import * as React from "react";
import axios from "axios";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";

function Profile() {
  const navigate = useNavigate();
  const user1 = useSelector((state) => state.user);
  const [user, setUser] = React.useState({});
  React.useEffect(() => {
    const fetchUser = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8084/users/getUserWithQuizList/${user1}`
        );
        setUser(response.data);
        console.log(response.data);
      } catch (error) {
        console.error("Error fetching quizzes:", error);
      }
    };
    fetchUser();
  }, []);

    const handleReviewQuiz = (quizIdinteraction) => {
      // Redirect to the desired location with the quizId
      navigate(`/reviewQuiz/${quizId}`);
    };

    const handlecreatequiz = () => {
      navigate(`/createQuiz`);
    }

    const handleSearchQuiz = () => {
      navigate(`/SearchQuizzes`);
    }

  return (
    <>
    <button onClick={handlecreatequiz}>Create Quiz</button>
    <button onClick={handleSearchQuiz}>Search Quiz</button>

      <h1> Name : {user.name} </h1>
      <h1> Email : {user.email} </h1>
      <h1> About : {user.about} </h1>
      <h1> Quizzes attempted </h1>
      {/* <h1> {JSON.stringify(user.listOfInteractions)}</h1> */}
      <table>
        <thead>
          <tr>
            <th>Quiz Title</th>
            <th>Quiz Creator</th>
            <th>Marks</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {Array.isArray(user.listOfInteractions) &&
            user.listOfInteractions.length > 0 &&
            user.listOfInteractions.map((interaction) => (
              <tr key={interaction.quizId}>
                <td>{interaction.quiz.title}</td>
                <td>{interaction.quiz.user.name}</td>
                <td>
                  {interaction.noOfCorrectAnswers} /{" "}
                  {interaction.quiz.numberOfQuestions}
                </td>
                <td>
                  <button onClick={handleReviewQuiz(interaction.quizId)(interaction)}> Review Quiz </button>
                </td>
              </tr>
            ))}
        </tbody>
      </table>
    </>
  );
}

export default Profile;