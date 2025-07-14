class AuthenticationService {
  registerSuccessfulLogin(username, password) {
    console.log("registerSuccessfulLogin");
    localStorage.setItem("user", username);
  }

  logout() {
    localStorage.removeItem("user");
  }

  isUserLoggedIn() {
    let user = localStorage.getItem("user");
    if (user === null) return false;
    return true;
  }
}

export default new AuthenticationService();
