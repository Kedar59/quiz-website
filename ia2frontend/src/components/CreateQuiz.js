import * as React from "react";
import axios from "axios";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";

function CreateQuiz() {
  const navigate = useNavigate();
  const user1 = useSelector((state) => state.user);
  const [title, setTitle] = React.useState("");
  const [numberOfQuestions, setNumberOfQuestions] = React.useState(0);
  const [user, setUser] = React.useState({userId:user1});
  const handleSubmit = async (event) => {
    // event.preventDefault();
    const payload = {
      title : title,
      numberOfQuestions : numberOfQuestions,
      user : user
    };
    if(!payload.title){
      alert("title is empty");
    } else if(!payload.numberOfQuestions){
      alert("number of questions is empty");
    } else if(!payload.user){
      alert("user not logged in");
    }
    console.log(payload);
    try {
      const response = await axios.post(
        "http://localhost:8084/quizzes/createQuiz",
        payload
      );
      
      if (response.status === 201) {
        // window.location.replace("/");
        // console.log("Registration successful:", response.data);
        const quiz = response.data;
        console.log(JSON.stringify(quiz));
        navigate(`/addQuestion/${quiz.quizId}/${quiz.numberOfQuestion}`);
      } else {
        alert("some error occoured : "+ response.status)
      }
      // Optionally, you can redirect the user or show a success message here
    } catch (error) {
      // Parse the response JSON if available
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
    <>
      <label>
        Title :
        <input
          value={title}
          onChange={e => setTitle(e.target.value)}
        />
      </label>
      <label>
        Number Of Questions:
        <input
          value={numberOfQuestions}
          onChange={e => {
            // console.log(`number of questions is updated from ${typeof e.target.value}`);
            setNumberOfQuestions(parseInt(e.target.value));
            // console.log(`number of questions is updated to ${typeof numberOfQuestions}`);
          }}
          type="number"
          min={1}
        />
      </label>
      <button onClick={()=>handleSubmit()}> Create Quiz </button>
    </>
  );
}

export default CreateQuiz;
