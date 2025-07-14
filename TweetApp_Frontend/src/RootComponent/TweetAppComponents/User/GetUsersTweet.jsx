import React, { useEffect, useState } from "react";
import { Card } from "react-bootstrap";
import avatar from "../../../profile-picture.jpg";
import { Form, FormGroup, Input, Label, Row } from "reactstrap";
import tweetService from "../../tweetAppService/tweet.service";
import TweetCard from "../tweet/TweetCard";
import NavBar from "../page/NavBar";
import "../common.css";
function GetUsersTweet() {
  const [tweets, setTweets] = useState([]);
  const [users, setUser] = useState([]);
  let username = JSON.parse(localStorage.getItem("user")).user.username;
  // const [userData, setUserData] = useState(null);
  // useEffect(() => {
  //   setUserData(JSON.parse(localStorage.getItem("user")));
  //   console.log(userData);
  // }, []);
  useEffect(() => {
    tweetService.getUserByUserName(username).then((res) => {
      console.log(res.data);
      setUser(res.data);
    });
    tweetService.getAllTweetOfUsername(username).then((res) => {
      console.log(res.data);
      setTweets(res.data);
    });
  }, []);

  return (
    <>
      <div>
        <NavBar />
      </div>
      <div style={{ display: "flex", justifyContent: "center" }}>
      <Card id="user-card" style={{ padding: "20px", marginTop: "10px",width: "300px" }}>
        <div className="fw-bold mb-4">Your Profile</div>
        <div key={users.username}>
        <img src={avatar} height="100" width="100" />
          <div className="row">
            <span className="col-12 justify-centre user">{users.username}</span>
          </div>
          <div className="email">
            <span>{users.emailId}</span>
          </div>
          <div className="contact">
            <span>{users.contactNumber}</span>
          </div>
        </div>
        <br />
      </Card>
      </div>
      <div className="mt-3" style={{ display: "flex", justifyContent: "center" }}>
        <Card variant="outlined" style={{ width: "500px", padding: "20px" }}>
          <Card.Title color="blue">
            Your Tweets
          </Card.Title>
          <Card.Body>
            <div>
              {tweets.length > 0 &&
                tweets.map((tweet) => (
                  <TweetCard
                    tweet={tweet}
                    key={tweet.tweetId}
                    userProfile={true}
                  />
                ))}
            </div>
          </Card.Body>
        </Card>
      </div>
    </>
  );
}

export default GetUsersTweet;
