import React, { useState } from 'react';
import axios from 'axios';
import { useDispatch } from 'react-redux';
import { useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { Button, TextField, Box, Typography, Paper } from '@mui/material';

function Login() {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const user = useSelector((state) => state.user);
  console.log("user here line 8 :" + user);

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleSubmit = async () => {
    const payload = {
      email: email,
      password: password,
    };
    try {
      const response = await axios.post("http://localhost:8084/users/login", payload);
      console.log(response.data);
      console.log("userId : " + response.data.userId);
      if (response.status === 200) {
        alert("logged in");
        dispatch({ type: 'LOGIN', payload: response.data.userId });
        navigate("/profile");
      } else if (response.status === 201) {
        alert("password didn't match");
      } else if (response.status === 202) {
        alert("email wasn't registered");
      }
    } catch (error) {
      throw new Error("error while logging in : " + error);
    }
  }

  return (
    <Box
      display="flex"
      justifyContent="center"
      alignItems="center"
      minHeight="100vh"
      component={Paper}
      elevation={3}
    >
      <Box
        p={4}
        display="flex"
        flexDirection="column"
        alignItems="center"
        width="100%"
        maxWidth={360}
      >
        <Typography variant="h4" component="h1" gutterBottom>
          Login
        </Typography>
        <TextField
          label="Email"
          type="email"
          name="email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          margin="normal"
          fullWidth
        />
        <TextField
          label="Password"
          type="password"
          name="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          margin="normal"
          fullWidth
        />
        <Button
          variant="contained"
          color="primary"
          onClick={handleSubmit}
          style={{ marginTop: 16 }}
          fullWidth
        >
          Login
        </Button>
        <Button variant="contained" color="secondary" onClick={()=>{
          navigate(`/register`);
        }}>
        Dont have a account
        </Button>
      </Box>
    </Box>
  );
}

export default Login;
