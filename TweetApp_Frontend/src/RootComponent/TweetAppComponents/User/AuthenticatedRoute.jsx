import React, { Component } from "react";
import { Route, Link } from "react-router-dom";
import AuthenticatedUser from "./AuthenticatedUser";

class AuthenticatedRoute extends Component {
  render() {
    return (
      <>
        AuthenticatedUser.isUserLoggedIn() ? (
        <Route {...this.props} />
        ) : (
        <Link to="/login" />
        );
      </>
    );
  }
}

export default AuthenticatedRoute;
