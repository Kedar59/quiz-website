import './App.css';
import React from 'react';
import Login from "./components/Login";
import Register from "./components/Register";
import CreateQuiz from "./components/CreateQuiz";
import AddQuestion from './components/AddQuestion';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import SearchQuizzes from './components/SearchQuizzes';
import AttemptQuiz from './components/AttemptQuiz';

function App() {
  return (
    <Router>
      <div>
        <Routes>
          <Route path="/" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="/createQuiz" element={<CreateQuiz />} />
          <Route path="/addQuestion/:id/:nQts" element={<AddQuestion />} />
          <Route path="/SearchQuizzes" element={<SearchQuizzes />} />
          <Route path="/attemptQuiz" element={<AttemptQuiz />} />
        </Routes>
        
      </div>
    </Router>
  );
}

export default App;
