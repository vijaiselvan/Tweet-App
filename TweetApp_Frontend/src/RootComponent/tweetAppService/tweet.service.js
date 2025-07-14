import axios from "axios";

const TWEET_REST_API_URL = "http://localhost:5000/api/v1.0/tweets/";
const USER_TOKEN = JSON.parse(localStorage.getItem("user"));

class tweetService {
  getAllTweets() {
    return axios.get(TWEET_REST_API_URL + "all", {
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + USER_TOKEN.token,
      },
    });
  }
  getAllUsers() {
    return axios.get(TWEET_REST_API_URL + "users/all", {
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + USER_TOKEN.token,
      },
    });
  }
  getUserByUsername(username) {
    return axios.get(TWEET_REST_API_URL + "user/search/" + username, {
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + USER_TOKEN.token,
      },
    });
  }
  getUserByUserName(username) {
    return axios.get(TWEET_REST_API_URL + "user/" + username, {
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + USER_TOKEN.token,
      },
    });
  }
  getTweetByTweetId(tweetId) {
    return axios.get(TWEET_REST_API_URL + "tweet/" + tweetId, {
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + USER_TOKEN.token,
      },
    });
  }

  getAllTweetOfUsername(username) {
    return axios.get(TWEET_REST_API_URL + username, {
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + USER_TOKEN.token,
      },
    });
  }

  postTweet(username, tweet, tweetTag) {
    return axios.post(
      TWEET_REST_API_URL + username + "/add",
      {
        username,
        tweet,
        tweetTag,
      },
      {
        headers: {
          "Content-Type": "application/json",
          Authorization: "Bearer " + USER_TOKEN.token,
        },
      }
    );
  }

  updateTweet = (username, tweetId, tweet) => {
    return axios.put(
      TWEET_REST_API_URL + "tweet/update",
      { username, tweetId, tweet },
      {
        headers: {
          "Content-Type": "application/json",
          Authorization: "Bearer " + USER_TOKEN.token,
        },
      }
    );
  };

  likeTweet(username, tweetId) {
    return axios.put(
      TWEET_REST_API_URL + username + "/like/" + tweetId,
      {
        username,
        tweetId,
      },
      {
        headers: {
          "Content-Type": "application/json",
          Authorization: "Bearer " + USER_TOKEN.token,
        },
      }
    );
  }

  unlikeTweet(username, tweetId) {
    return axios.put(
      TWEET_REST_API_URL + username + "/unlike/" + tweetId,
      {
        username,
        tweetId,
      },
      {
        headers: {
          "Content-Type": "application/json",
          Authorization: "Bearer " + USER_TOKEN.token,
        },
      }
    );
  }

  replyTweet(username, tweetId, tweetReply) {
    return axios.post(
      TWEET_REST_API_URL + username + "/reply/" + tweetId,
      {
        username,
        tweetId,
        tweetReply,
      },
      {
        headers: {
          "Content-Type": "application/json",
          Authorization: "Bearer " + USER_TOKEN.token,
        },
      }
    );
  }

  deleteTweet(username, tweetId) {
    return axios.delete(TWEET_REST_API_URL + username + "/delete/" + tweetId, {
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + USER_TOKEN.token,
      },
    });
  }
}
export default new tweetService();
