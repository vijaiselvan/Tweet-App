import React, { useState } from "react";
import { NavLink } from "react-router-dom";
import Footer from "./Footer";
import Header from "./Header";
import "./home.css";
import Login from "./Login";
import Register from "./Register";
function Home() {
  const [isSignup, setIsSignup] = useState(true);

  const toggleForm = () => {
    setIsSignup((prevState) => !prevState);
  };
  return (
    <div>
      <Header />
      <nav id="nav-root" className="navbar navbar-expand-lg navbar-dark bg-dark">
  <div className="container-fluid">
    <a className="navbar-brand" href="#">Tweet App</a>
    <div id="navbarNavAltMarkup">
      <div className="navbar-nav">
        <a className={`nav-link ${!isSignup ? "active" : "disabled"}`} aria-current="page" href="#" onClick={toggleForm}
            >Register</a>
        <a className={`nav-link ${isSignup ? "active" : "disabled"}`} href="#"  onClick={toggleForm}
          >Login</a>
      </div>
    </div>
  </div>
</nav>

        <div className="home-page w-full mt-4">
          {isSignup && <Register />}
          {!isSignup && <Login />}
        </div>
        {/* <NavLink to="/">Login</NavLink>
      <NavLink to="/"> Register</NavLink> */}

    </div>
  );
}

export default Home;
