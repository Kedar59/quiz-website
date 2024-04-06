import React, { useEffect, useState } from "react";
import axios from "axios";
import { useParams, useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";
import { Button, Typography, Paper, Box, Divider, List, ListItem, ListItemText } from '@mui/material';

function Review() {
  const navigate = useNavigate();
  const user1 = useSelector((state) => state.user);
  const { quizId } = useParams();
  const [quizReview, setQuizReview] = useState({});

  useEffect(() => {
    const fetchUser = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8084/users/reviewQuiz/${user1}/${quizId}`
        );
        setQuizReview(response.data);
        console.log(response.data);
      } catch (error) {
        console.error("Error fetching quiz:", error);
      }
    };
    fetchUser();
  }, [quizId, user1]);

  const handleProfile = () => {
    navigate(`/profile`);
  };

  return (
    <Box sx={{ width: '100%', maxWidth: 600, margin: 'auto', mt: 2 }}>
      <Paper elevation={3} sx={{ p: 2 }}>
        <Button variant="contained" onClick={handleProfile} sx={{ mb: 2 }}>
          Back to profile
        </Button>
        {quizReview.interaction && quizReview.interaction.quiz && quizReview.interaction.quiz.numberOfQuestions && (
          <>
            <Typography variant="h4" gutterBottom>
              Quiz Title: {quizReview.interaction.quiz.title}
            </Typography>
            <Typography variant="h5">
              Score: {quizReview.interaction.noOfCorrectAnswers} / {quizReview.interaction.quiz.numberOfQuestions}
            </Typography>
            <Divider sx={{ my: 2 }} />
            <List>
              {Array.isArray(quizReview.listQts) &&
                quizReview.listQts.map((qts, index) => (
                  <React.Fragment key={qts.question.questionId}>
                    <ListItem>
                      <ListItemText primary={`Question ${index + 1}: ${qts.question.question}`} secondary={
                        <>
                          {`Option 1: ${qts.question.option1}`}
                          <br />
                          {`Option 2: ${qts.question.option2}`}
                          <br />
                          {`Option 3: ${qts.question.option3}`}
                          <br />
                          {`Option 4: ${qts.question.option4}`}
                          <br />
                          {`Correct answer: ${qts.question.answer}`}
                          <br />
                          {`Your answer: ${qts.userAnswer}`}
                        </>
                      } />
                    </ListItem>
                    <Divider component="li" />
                  </React.Fragment>
                ))}
            </List>
          </>
        )}
      </Paper>
    </Box>
  );
}

export default Review;
