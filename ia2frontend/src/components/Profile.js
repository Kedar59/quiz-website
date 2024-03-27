import * as React from "react";
import axios from "axios";
import { useSelector } from "react-redux";
function Profile() {
  const [user, setUser] = React.useState({});
  const user1 = useSelector((state) => state.user);
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

  return (
    <>
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
                  <button> Review Quiz </button>
                </td>
              </tr>
            ))}
        </tbody>
      </table>
    </>
  );
}

export default Profile;