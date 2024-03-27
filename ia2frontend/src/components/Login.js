import * as React from "react";
import axios from "axios";
import { useDispatch } from "react-redux";
import { useSelector } from "react-redux";
// import MenuItem from '@mui/material/MenuItem';
// import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";
function Login() {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const user = useSelector((state) => state.user);
  console.log("user here line 8 :"+user);
  const [email, setEmail] = React.useState("");
  const [password, setPassword] = React.useState("");
  const handleSubmit = async () => {
    const payload = {
      email : email,
      password : password
    }
    try{
      const response = await axios.post("http://localhost:8084/users/login",payload);
      console.log(response.data);
      console.log("userId : "+response.data.userId);
      if(response.status === 200){
        alert("logged in");
        dispatch({ type: 'LOGIN', payload: response.data.userId }); // Dispatch LOGIN action
        navigate("/profile");
      } else if(response.status === 201){
        alert("password didnt match");
      } else if(response.status === 202){
        alert("email wasnt registered");
      }
    }catch(error){
      throw new Error("error while logging in : "+error);
    }
  }
  return (
    <>
      <label>email</label>
      <input
        type="text"
        name="email"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
        placeholder="Email"
      />
      <label>password</label>
      <input
        type="text"
        name="password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
        placeholder="Password"
      />
      <button onClick={handleSubmit} > Login </button>
    </>
  );
}
export default Login;
