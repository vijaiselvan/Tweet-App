import React, { useState } from "react";
import { NavItem } from "react-bootstrap";
import { NavLink, useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import authService from "../../tweetAppService/authService";
import "./page.css";
function Register() {
  const [loginId, setloginId] = useState("");
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [emailId, setEmailId] = useState("");
  const [username, setUserName] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [contactNumber, setContactNumber] = useState("");
  //const [users, setUsers] = useState([]);
  const navigate = useNavigate();
  function handleRegister() {
    authService
      .register(
        loginId,
        firstName,
        lastName,
        emailId,
        username,
        password,
        confirmPassword,
        contactNumber
      )
      .then((res) => {
        console.log("test", res.data);
        //setUsers(res.data);

        if (res.data) {
          toast.success("Register Successful");
          navigate("/");
        } else {
          toast.warning(res.data.msg);
        }
      });
  }
  function handleInputChange(event) {
    const { id, value } = event.target;
    if (id === "loginId") {
      setloginId(value);
    } else if (id === "firstName") {
      setFirstName(value);
    } else if (id === "lastName") {
      setLastName(value);
    } else if (id === "email") {
      setEmailId(value);
    } else if (id === "username") {
      setUserName(value);
    } else if (id === "password") {
      setPassword(value);
    } else if (id === "confirmPassword") {
      setConfirmPassword(value);
    } else if (id === "contactNumber") {
      setContactNumber(value);
    }
  }
  return (
    <div>
      <div className="form">
        <div className="form-body">
          <div className="head mb-3">
            <h1>
              <b>TWEET APPLICATION</b>
            </h1>
          </div>
          <div className="loginId">
            <label className="form__label col-4" htmlFor="loginId">
              Login Id
            </label>
            <input
              className="form__input col-8" 
              type="text"
              id="loginId"
              required
              value={loginId}
              onChange={(e) => handleInputChange(e)}
              placeholder="Enter your Login Id"
            />
          </div>
          <div className="username">
            <label className="form__label col-4" htmlFor="firstName">
              First Name
            </label>
            <input
              className="form__input col-8"
              type="text"
              id="firstName"
              required
              value={firstName}
              onChange={(e) => handleInputChange(e)}
              placeholder="Enter your FirstName"
            />
          </div>
          <div className="lastname">
            <label className="form__label col-4" htmlFor="lastName">
              Last Name
            </label>
            <input
              type="text"
              name=""
              id="lastName"
              required
              value={lastName}
              className="form__input col-8"
              onChange={(e) => handleInputChange(e)}
              placeholder="Enter your LastName"
            />
          </div>
          <div className="email">
            <label className="form__label col-4" htmlFor="email">
              Email
            </label>
            <input
              type="email"
              id="email"
              className="form__input col-8"
              value={emailId}
              required
              onChange={(e) => handleInputChange(e)}
              placeholder="Enter your Email"
            />
          </div>
          <div className="username">
            <label className="form__label col-4" htmlFor="username">
              Username
            </label>
            <input
              className="form__input col-8"
              type="text"
              id="username"
              value={username}
              required
              onChange={(e) => handleInputChange(e)}
              placeholder="Enter your Username"
            />
          </div>
          <div className="password">
            <label className="form__label col-4" htmlFor="password">
              Password
            </label>
            <input
              className="form__input col-8"
              type="password"
              id="password"
              required
              value={password}
              onChange={(e) => handleInputChange(e)}
              placeholder="Enter your Password"
            />
          </div>
          <div className="confirm-password">
            <label className="form__label col-4" htmlFor="confirmPassword">
              Confirm Password
            </label>
            <input
              className="form__input col-8"
              type="password"
              id="confirmPassword"
              required
              value={confirmPassword}
              onChange={(e) => handleInputChange(e)}
              placeholder="Reenter your Password"
            />
          </div>
          <div className="contact-number">
            <label className="form__label col-4" htmlFor="contactNumber">
              Contact Number
            </label>
            <input
              className="form__input col-8"
              type="text"
              id="contactNumber"
              required
              value={contactNumber}
              onChange={(e) => handleInputChange(e)}
              placeholder="Enter your Contact Number"
            />
          </div>
        </div>
        <div>
          <button
            type="submit"
            id="submit"
            onClick={handleRegister}
            className="btn btn-info col-5 mb-4"
          >
            Register
          </button>
        </div>

        {/* <NavItem>
          Already have an account?
          <NavLink to="/signup">Login</NavLink>
        </NavItem> */}
      </div>
    </div>
  );
}

export default Register;
