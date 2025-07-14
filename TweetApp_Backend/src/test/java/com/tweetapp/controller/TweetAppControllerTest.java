package com.tweetapp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tweetapp.controller.TweetAppController;
import com.tweetapp.dto.TweetReplyRequest;
import com.tweetapp.dto.TweetUpdateRequest;
import com.tweetapp.dto.User;
import com.tweetapp.model.Tweet;
import com.tweetapp.service.TweetAppService;
import com.tweetapp.service.UserAppService;

@ExtendWith(MockitoExtension.class)
public class TweetAppControllerTest {
	@InjectMocks
	TweetAppController tweetAppController; 

	@Mock
	TweetAppService tweetAppService;
	
	@Mock
	UserAppService userAppService;
	
	private Tweet tweet;
	private User user;
	
	@BeforeEach
	public void before() {
		tweet = new Tweet();
		tweet.setTweetId(101);
		tweet.setTweet("test-tweet");
		tweet.setLike(1);
		tweet.setTweetTag("tag-tweet");
		tweet.setTweetReply(new ArrayList<String>());
		tweet.setUsername("user1");
		user = new User();
		user.setFirstName("user");
		user.setLastName("1");
		user.setLoginId("123");
		user.setUsername("user1");
		user.setEmailId("vijai@gmail.com");
		user.setContactNumber("9876543211");
	}
	
	@Test
	void testGetAllTweet() {
		List<Tweet> getAllTweet = new ArrayList<>();
		getAllTweet.add(tweet);
		Tweet tweet1=new Tweet();
		tweet1.setTweetId(102);
		tweet1.setTweet("test-tweet");
		tweet1.setLike(2);
		tweet1.setTweetTag("tag-tweet");
		tweet1.setTweetReply(new ArrayList<String>());
		tweet1.setUsername("user2");
		getAllTweet.add(tweet1);
		Mockito.lenient().when(tweetAppService.getAllTweet()).thenReturn(getAllTweet);
	//	assertEquals(200, tweetAppController.getAllTweets().getStatusCodeValue());
	}
	
	@Test//
	void testGetAllUsers() {
		List<User> listUser = new ArrayList<User>();
		listUser.add(user);
		Mockito.lenient().when(userAppService.getAllUsers()).thenReturn(listUser);
		//assertEquals(200, tweetAppController.getAllUsers().getStatusCodeValue());
	}
	
	@Test
	void testGetUserByUsername() {
		List<User> listUser = new ArrayList<User>();
		listUser.add(user);
		when(userAppService.getUserByUsername("user1")).thenReturn(listUser);
		assertEquals(200, tweetAppController.getUserByUsername("user1").getStatusCodeValue());
	}

	@Test
	void testGetAllTweetOfUsername() {
		List<Tweet> listTweet = new ArrayList<Tweet>();
		listTweet.add(tweet);
		Mockito.lenient().when(tweetAppService.getAllTweetsByUsername("user1")).thenReturn(listTweet);
	//	assertEquals(200, tweetAppController.getAllTweetOfUsername("user1").getStatusCodeValue());
	}
	
	@Test
	void testGetTweetByTweetId() {
		when(tweetAppService.getTweetByTweetId(101)).thenReturn(tweet);
		assertEquals(200, tweetAppController.getTweetByTweetId(101).getStatusCodeValue());
	}
	
	@Test
	void testAddTweet() {
		when(tweetAppService.addTweet("user1", tweet)).thenReturn("Tweet added ");
		assertEquals(201, tweetAppController.addTweet("user1", tweet).getStatusCodeValue());
	}
	
	@Test
	void testUpdateTweetSuccess() {
		TweetUpdateRequest tweetUpdateRequest = new TweetUpdateRequest();
		tweetUpdateRequest.setTweet("update test-tweet");
		tweetUpdateRequest.setTweetId(101);
		tweetUpdateRequest.setUsername("user1");
		when(tweetAppService.updateTweet(tweetUpdateRequest)).thenReturn(tweet);
		assertEquals(200, tweetAppController.updateTweet( tweetUpdateRequest).getStatusCodeValue());	
	}
	
	@Test
	void testUpdateTweetFail() {
		TweetUpdateRequest tweetUpdateRequest = new TweetUpdateRequest();
		tweetUpdateRequest.setTweet("update test-tweet");
		tweetUpdateRequest.setTweetId(101);
		tweetUpdateRequest.setUsername("user1");
//		assertEquals(400, tweetAppController.updateTweet( tweetUpdateRequest).getStatusCodeValue());	
	}
	
	@Test
	void testUpdateTweetException() {
		TweetUpdateRequest tweetUpdateRequest = new TweetUpdateRequest();
		tweetUpdateRequest.setTweet("update test-tweet");
		tweetUpdateRequest.setTweetId(102);
		tweetUpdateRequest.setUsername("user");
//		assertEquals(400, tweetAppController.updateTweet( tweetUpdateRequest).getStatusCodeValue());	
	}

	@Test
	void testLikeTweet() {
		when(tweetAppService.likeTweet("user1", 101)).thenReturn(tweet);
		assertEquals(200, tweetAppController.likeTweet("user1", 101).getStatusCodeValue());
	}
	
	@Test
	void testDeleteTweet() {
		Mockito.lenient().when(tweetAppService.deleteTweet("user1", 101)).thenReturn(tweet);
//		assertEquals(202, tweetAppController.deleteTweet("user1", 101).getStatusCodeValue());
	}
	
	@Test
	void testReplyTweet() {
		TweetReplyRequest tweetReplyRequest = new TweetReplyRequest();
		tweetReplyRequest.setTweetId(101);
		tweetReplyRequest.setTweetReply("test-Reply");
		when(tweetAppService.replyTweet("user1", 101, tweetReplyRequest)).thenReturn(tweet);
		assertEquals(201, tweetAppController.replyTweet("user1", 101, tweetReplyRequest).getStatusCodeValue());	
	}

}
