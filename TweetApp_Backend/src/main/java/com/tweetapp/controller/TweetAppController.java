package com.tweetapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.dto.TweetReplyRequest;
import com.tweetapp.dto.TweetUpdateRequest;
import com.tweetapp.dto.User;
import com.tweetapp.model.Tweet;
import com.tweetapp.service.TweetAppService;
import com.tweetapp.service.UserAppService;

@CrossOrigin(origins = "http://localhost:3000/") //2.7.2
//@CrossOrigin(origins = "*") //2.7.2
@RestController
@RequestMapping("/api/v1.0/tweets/")
public class TweetAppController {

	@Autowired
	TweetAppService tweetAppService;

	@Autowired
	UserAppService userAppService;

	@GetMapping(value = "/all")
	public List<Tweet> getAllTweets() {
		List<Tweet> tweet = tweetAppService.getAllTweet();

		return tweet;
	}

	@GetMapping(value = "/users/all")
	public List<User> getAllUsers() {
		List<User> users = userAppService.getAllUsers();
		return users;
	}

	@GetMapping(value = "/user/search/{username}")
	public ResponseEntity<List<User>> getUserByUsername(@PathVariable String username) {
		List<User> user = userAppService.getUserByUsername(username);
		ResponseEntity<List<User>> responseEntity = new ResponseEntity<>(user, HttpStatus.OK);
		return responseEntity;
	}

	@GetMapping(value = "/user/{username}")
	public ResponseEntity<User> getUserByUserName(@PathVariable String username) {
		User user = userAppService.getUserByUserName(username);
		ResponseEntity<User> responseEntity = new ResponseEntity<>(user, HttpStatus.OK);
		return responseEntity;
	}

	@GetMapping(value = "/{username}")
	public List<Tweet> getAllTweetOfUsername(@PathVariable String username) {
		List<Tweet> tweets = tweetAppService.getAllTweetsByUsername(username);
		return tweets;
	}

	@GetMapping(value = "/tweet")
	public ResponseEntity<Tweet> getTweetByTweetId(@RequestParam Integer tweetId) {
		Tweet tweets = tweetAppService.getTweetByTweetId(tweetId);
		ResponseEntity<Tweet> responseEntity = new ResponseEntity<>(tweets, HttpStatus.OK);
		return responseEntity;
	}

	@PostMapping(value = "/{username}/add")
	public ResponseEntity<String> addTweet(@PathVariable String username, @RequestBody Tweet tweet) {
		String tweet1 = tweetAppService.addTweet(username, tweet);
		ResponseEntity<String> responseEntity = new ResponseEntity<>(tweet1, HttpStatus.CREATED);
		return responseEntity;
	}

	@PutMapping(value = "/tweet/update")
	public ResponseEntity<Tweet> updateTweet(@RequestBody TweetUpdateRequest tweetUpdateRequest) {
		Tweet update = null;
		ResponseEntity<Tweet> responseEntity = null;
		update = tweetAppService.updateTweet(tweetUpdateRequest);
		responseEntity = new ResponseEntity<>(update, HttpStatus.OK);

		return responseEntity;
	}

	@DeleteMapping(value = "/{username}/delete/{id}")
	public ResponseEntity<Tweet> deleteTweet(@PathVariable String username, @PathVariable Integer id) {
		Tweet tweet1 = tweetAppService.deleteTweet(username, id);
		ResponseEntity<Tweet> responseEntity = new ResponseEntity<>(tweet1, HttpStatus.OK);
		return responseEntity;
	}

	@PutMapping(value = "/{username}/like/{id}")
	public ResponseEntity<Tweet> likeTweet(@PathVariable String username, @PathVariable Integer id) {
		Tweet tweet1 = tweetAppService.likeTweet(username, id);
		ResponseEntity<Tweet> responseEntity = new ResponseEntity<>(tweet1, HttpStatus.OK);
		return responseEntity;
	}

	@PutMapping(value = "/{username}/unlike/{id}")
	public ResponseEntity<Tweet> unlikeTweet(@PathVariable String username, @PathVariable Integer id) {
		Tweet tweet1 = tweetAppService.unlikeTweet(username, id);
		ResponseEntity<Tweet> responseEntity = new ResponseEntity<>(tweet1, HttpStatus.OK);
		return responseEntity;
	}

	@PostMapping(value = "/{username}/reply/{id}")
	public ResponseEntity<Tweet> replyTweet(@PathVariable String username, @PathVariable Integer id,
			@RequestBody TweetReplyRequest tweetReplayRequest) {
		Tweet tweet1 = tweetAppService.replyTweet(username, id, tweetReplayRequest);
		ResponseEntity<Tweet> responseEntity = new ResponseEntity<>(tweet1, HttpStatus.CREATED);
		return responseEntity;
	}

}
