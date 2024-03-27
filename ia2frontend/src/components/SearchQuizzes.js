import * as React from "react";
import axios from "axios";
function SearchQuizzes() {
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
            <tr key={quiz.quizId}>
              <td>{quiz.title}</td>
              <td>{quiz.user.name}</td>
              <td>{quiz.numberOfQuestions}</td>
              <td>
                <button
                  onClick={async () => {
                    const payload = {
                      quizId: quiz.quizId,
                      userId: "01efbab3-6c95-437e-b556-3d3f528d2d47",
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
                      } else if (response.status === 201) {
                        alert(response.data.message);
                      }
                    } catch (error) {
                      throw new Error("error while registering user "+error);
                    }
                  }}
                >
                  Attempt Quiz
                  {/* navigate to attempt quiz with quizId */}
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </>
  );
}
export default SearchQuizzes;
