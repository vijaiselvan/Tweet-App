import React from "react";
import NavBar from "../page/NavBar";
import GetAllTweet from "./GetAllTweet";
import PostTweet from "./PostTweet";
import authService from "../../tweetAppService/authService";
import './tweet.css';
const TabBar = () => {
  return (
    <div>
      <div>
        <NavBar />
      </div>
      <div className="fw-bold" style={{color:"white"}}><span className="text-uppercase">Hi {authService.currentUser()}!</span> WELCOME TO TWEET APP</div>
      <div className="row mt-5">
        <div className="col-9 justify-content-end">
          <GetAllTweet />
        </div>
        <div className="posting">
          <PostTweet />
        </div>
        {/* <br /> */}
      </div>
    </div>
  );
};
export default TabBar;
