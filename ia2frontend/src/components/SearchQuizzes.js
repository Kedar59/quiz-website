import * as React from "react";
import axios from "axios";
function SearchQuizzes() {
  const [quizzes, setQuizzes] = React.useState([]);
  React.useEffect(() => {
    const fetchQuizzes = async () => {
      try {
        const response = await axios.get("http://localhost:8084/quizzes/getAllQuizzes");
        setQuizzes(response.data);
        console.log(JSON.stringify(response.data));
      } catch (error) {
        console.error("Error fetching quizzes:", error);
      }
    };

    fetchQuizzes();
  }, []);
  return (
    <>
      <table>
      <thead>
        <tr>
          <th>Quiz Title</th>
          <th>Quiz Creator</th>
          <th>Number of Questions</th>
          <th>Action</th>
        </tr>
      </thead>
      <tbody>
        {quizzes.map((quiz) => (
          <tr key={quiz.id}>
            <td>{quiz.title}</td>
            <td>{quiz.user.name}</td>
            <td>{quiz.numOfQuestions}</td>
            <td>
                <button>Attempt Quiz</button>
            </td>
          </tr>
        ))}
      </tbody>
    </table>
    </>
  )
}
export default SearchQuizzes;
