import "./App.css";
import { Route, Routes } from "react-router-dom";
import TweetApp from "./RootComponent/TweetAppComponents/tweet/TweetApp";
import ResetPassword from "./RootComponent/TweetAppComponents/page/ResetPassword";
import Home from "./RootComponent/TweetAppComponents/page/Home";
import GetAllUsers from "./RootComponent/TweetAppComponents/User/GetAllUsers";
import SearchUser from "./RootComponent/TweetAppComponents/User/SearchUser";
import { useEffect, useState } from "react";
import GetUsersTweet from "./RootComponent/TweetAppComponents/User/GetUsersTweet";

import "react-toastify/dist/ReactToastify.css";
import { ToastContainer } from "react-toastify";
import RegisterForm from "./RootComponent/TweetAppComponents/page/RegisterForm";
function App() {
  const [isLoggedInUser, setIsLoggedInUser] = useState(false);
  useEffect(() => {
    if (localStorage.getItem("user") != null) {
      setIsLoggedInUser(true);
    } else {
      setIsLoggedInUser(false);
    }
  });
  return (
    <div className="App">
      <Routes>
        <Route path="/" exact element={<Home />} />
        <Route path="/signup" element={<RegisterForm />} />
        {isLoggedInUser}? (<Route path="/tweet" element={<TweetApp />} />
        <Route path="/users" element={<GetAllUsers />} />
        <Route path="/profile" element={<GetUsersTweet />} />
        <Route path="/search/users" element={<SearchUser />} />
        <Route path="/forgot" element={<ResetPassword />} />
        ):
        <Route path="/" exact element={<Home />} />
      </Routes>
      <ToastContainer />
    </div>
  );
}

export default App;
