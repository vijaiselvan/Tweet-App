import React, { useState } from "react";
import { useEffect } from "react";
import { Card } from "react-bootstrap";
//import { AiOutlineSend } from "react-icons/ai";
import { Form, FormGroup, Input, Label } from "reactstrap";
import tweetService from "../../tweetAppService/tweet.service";
import { toast } from "react-toastify";
import "./tweet.css";
const PostTweet = () => {
  const [userData, setUserData] = useState(null);
  const [tweet, setTweetMsg] = useState("");

  const [tweetTag, setTweetTag] = useState("");
  const [remaining, setRemaining] = useState(144);

  useEffect(() => {
    setRemaining(144 - tweet.length);
  }, [tweet]);

  useEffect(() => {
    setUserData(JSON.parse(localStorage.getItem("user")));
  }, []);
  function handlePostTweet() {
    tweetService
      .postTweet(userData.user.username, tweet, tweetTag)
      .then((response) => {
        console.log(response.data);
        if (response.data) {
          toast.success("Tweet Posted successfully");
        } else {
          toast.error("Tweet failed to post!");
        }
      });
  }

  return (
    <div style={{ display: "flex", justifyContent: "center" }}>
      <Card id="card" variant="outlined" style={{ width: "30rem" }}>
        <div className="post-tweet">
          <Card.Body>
            <Form>
              <FormGroup>
                <Label className="fw-bold" for="exampleText">You can post your tweet here</Label>
                <Input
                  id="exampleText"
                  name="text"
                  type="textarea"
                  maxLength={144}
                  value={tweet.tweet}
                  required
                  onChange={(e) => setTweetMsg(e.target.value)}
                  placeholder="What is your tweet for today?"
                />
              <div className="mt-2">{remaining} characters remaining for your tweet</div>
              </FormGroup>

              <FormGroup>
                <Input
                  id="TweetTag"
                  name="tag"
                  maxLength={50}
                  placeholder="#HashTag"
                  onChange={(e) => setTweetTag(e.target.value)}
                  type="text"
                />
              </FormGroup>

              <button
              id="btn"
                type="button"
                className="btn btn-primary btn-sm"
                onClick={handlePostTweet}
              >
                Post
              </button>
            </Form>
          </Card.Body>
        </div>
      </Card>
    </div>
  );
};
export default PostTweet;
