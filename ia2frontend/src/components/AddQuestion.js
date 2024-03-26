import * as React from "react";
import axios from "axios";
import { useParams } from "react-router-dom";
function AddQuestion() {
  const { id } = useParams();
  const { nQts } = useParams();
  const [questions, setQuestions] = React.useState([]);

  React.useEffect(() => {
    // Initialize questions array based on nQts
    const initialQuestions = Array.from({ length: parseInt(nQts) }, () => ({
      question: "",
      quiz: {
        quizId: id,
      },
      option1: "",
      option2: "",
      option3: "",
      option4: "",
      answer: 0,
    }));
    setQuestions(initialQuestions);
  }, [nQts, id]);

  const handleQuestionChange = (index, event) => {
    const { name, value } = event.target;
    console.log(JSON.stringify(name+ "  " + value));
    const updatedQuestions = [...questions];
    if(name==="answer"){
      // console.log(`integer editing ${typeof parseInt(value)}`);
      updatedQuestions[index][name] = parseInt(value);
    } else {
      // console.log(`string editing ${typeof value}`);
      updatedQuestions[index][name] = value;
    }
    
    setQuestions(updatedQuestions);

  };
  const handleSubmit = async () => {
    console.log(JSON.stringify(questions));
    try {
      const response = await axios.post('http://localhost:8084/quizzes/addQuestions', questions);
      if(response.status === 201){
        alert("questions added successfullt");
      }
    } catch (error) {
        throw new Error("while adding questions : " + error.message);
    }
  };
  return (

    <>
      <h1>Quiz Id : {id}</h1>
      <h2>Add Questions</h2>
      {questions.map((question, index) => (
        <div key={index}>
          <h3>Question {index + 1}</h3>
          <label>Question</label>
          <input
            type="text"
            name="question"
            value={question.question}
            onChange={(e) => handleQuestionChange(index, e)}
            placeholder="Enter question"
          />
          <br />
          <label>option 1</label>
          <input
            type="text"
            name="option1"
            value={question.option1}
            onChange={(e) => handleQuestionChange(index, e)}
            placeholder="Option 1"
          />
          <label>option 2</label>
          <input
            type="text"
            name="option2"
            value={question.option2}
            onChange={(e) => handleQuestionChange(index, e)}
            placeholder="Option 2"
          />
          <label>option 3</label>
          <input
            type="text"
            name="option3"
            value={question.option3}
            onChange={(e) => handleQuestionChange(index, e)}
            placeholder="Option 3"
          />
          <label>option 4</label>
          <input
            type="text"
            name="option4"
            value={question.option4}
            onChange={(e) => handleQuestionChange(index, e)}
            placeholder="Option 4"
          />
          <label>option 4</label>
          <input
            type="number"
            name="answer"
            value={question.answer}
            onChange={(e) => handleQuestionChange(index, e)}
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
export default AddQuestion;