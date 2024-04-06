import React, { useEffect, useState } from "react";
import axios from "axios";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { Button, Typography, Paper, Table, TableBody, TableCell, TableHead, TableRow, Box } from '@mui/material';

function Profile() {
  const navigate = useNavigate();
  const user1 = useSelector((state) => state.user);
  const [user, setUser] = useState({});
  
  useEffect(() => {
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
  }, [user1]);

  const handleReviewQuiz = (quizId) => {
    navigate(`/reviewQuiz/${quizId}`);
  };

  const handleCreateQuiz = () => {
    navigate(`/createQuiz`);
  };

  const handleSearchQuiz = () => {
    navigate(`/SearchQuizzes`);
  };

  return (
    <Box component={Paper} elevation={3} p={3}>
      <Typography variant="h4" gutterBottom>
        Profile
      </Typography>
      <Typography variant="h6">Name: {user.name}</Typography>
      <Typography variant="h6">Email: {user.email}</Typography>
      <Typography variant="h6">About: {user.about}</Typography>
      <Button variant="contained" color="primary" onClick={handleCreateQuiz} style={{ marginRight: 10 }}>
        Create Quiz
      </Button>
      <Button variant="contained" color="secondary" onClick={handleSearchQuiz}>
        Search Quiz
      </Button>

      <Typography variant="h6" style={{ marginTop: 20 }}>
        Quizzes attempted
      </Typography>
      <Table>
        <TableHead>
          <TableRow>
            <TableCell>Quiz Title</TableCell>
            <TableCell>Quiz Creator</TableCell>
            <TableCell>Marks</TableCell>
            <TableCell>Action</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {Array.isArray(user.listOfInteractions) && user.listOfInteractions.length > 0 && user.listOfInteractions.map((interaction) => (
            <TableRow key={interaction.quizId}>
              <TableCell>{interaction.quiz.title}</TableCell>
              <TableCell>{interaction.quiz.user.name}</TableCell>
              <TableCell>
                {interaction.noOfCorrectAnswers} / {interaction.quiz.numberOfQuestions}
              </TableCell>
              <TableCell>
                <Button variant="outlined" color="primary" onClick={() => handleReviewQuiz(interaction.quizId)}>
                  Review Quiz
                </Button>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </Box>
  );
}

export default Profile;
