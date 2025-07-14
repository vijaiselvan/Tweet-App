import React, { useState } from "react";
import { Card } from "react-bootstrap";
import profile from "../../../profile-picture.png";
import { FcLike, FcComments, FcLikePlaceholder } from "react-icons/fc";
import DeleteTweet from "./DeleteTweet";
import UpdateTweet from "./UpdateTweet";
import TweetReply from "./TweetReply";
import ReplyCard from "./ReplyCard";
import tweetService from "../../tweetAppService/tweet.service";
import './tweet.css'
const TweetCard = ({ tweet, userProfile }) => {
  const [replies, setReplies] = useState(false);
  const [like, setLike] = useState(false);
  // const [count,setCount] =
  const showReplies = () => {
    setReplies((prevState) => !prevState);
  };
  const handleLikeTweet = (event) => {
    event.preventDefault();
    tweetService.likeTweet(tweet.username, tweet.tweetId).then((res) => {
      console.log(res.data);
      window.location.reload(false);
    });
  };
  const handleUnLikeTweet = (event) => {
    event.preventDefault();
    tweetService.unlikeTweet(tweet.username, tweet.tweetId).then((res) => {
      console.log(res.data);
      window.location.reload(false);
    });
  };
  return (
    <div>
      <Card style={{ padding: "20px", marginTop: "10px" }}>
        <div key={tweet.tweetId}>
        <img src={profile} height="100" width="100" />
        <div className="row">
        {/* <img className="col-3" src={avatar} alt="Profile avatar" /> */}
        <span className="col-12 justify-centre user-name"> {tweet.username} </span>
        <span className="col-12">
      {userProfile && <UpdateTweet tweet={tweet} />}
        </span>
        </div>
        <div>
        <span>{tweet.tweet}</span>
        </div>
        <div className="tweet-tag">
        <span>{tweet.tweetTag}</span>
        </div>
        <div>
        <a href="#" onClick={showReplies}>
        <span>
        <FcComments size={28} />
        </span>
        </a>
      {tweet.tweetReply.length}
      {/* <span>{tweet.reply}</span> */}
        <a className="like"
        onChange={(e) => setLike(!like)}
        onClick={(event) => {
        like?handleUnLikeTweet(event): handleLikeTweet(event);
      }}
        >
      {like?<FcLikePlaceholder size={28} />: <FcLike size={28} />}
        </a>

      {tweet.like}

      {userProfile && <DeleteTweet tweet={tweet} />}
        </div>
        </div>
        <div style={{ float: "left" }}></div>
      </Card>
      {replies && (
        <>
          <div style={{ display: "flex", justifyContent: "right" }}>
            <Card style={{ padding: "20px", marginTop: "1px", width: "300px" }}>
              <TweetReply tweet={tweet} />

              {tweet.tweetReply.length > 0 &&
                tweet.tweetReply.map((reply) => (
                  <ReplyCard reply={reply} tweet={tweet} key={tweet.tweetId} />
                ))}
            </Card>
          </div>
        </>
      )}
    </div>
  );
};
export default TweetCard;
