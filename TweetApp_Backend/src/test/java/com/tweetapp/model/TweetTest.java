package com.tweetapp.model;



import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tweetapp.model.Tweet;

@ExtendWith(MockitoExtension.class)
class TweetTest {
	
	private Tweet tweet;

	@BeforeEach
	void before() {
		tweet = new Tweet();
		tweet.setTweetId(101);
		tweet.setTweet("tweet-1");
		tweet.setLike(2);
		tweet.setTweetTag("tag-1");
		List<String> tweetReply = new ArrayList<>();
		tweetReply.add("reply-1");
		tweet.setTweetReply(tweetReply);
		tweet.setUsername("user-1");
		
	}
	
	@Test
	void testGetTweetId() {
		assertEquals(101, tweet.getTweetId());
	}
	
	@Test
	void testGetTweet() {
		assertEquals("tweet-1", tweet.getTweet());
	}
	
	@Test
	void testGetTweetTag() {
		assertEquals("tag-1", tweet.getTweetTag());
	}
	
	@Test
	void testGetTweetReply() {
		assertEquals("reply-1", tweet.getTweetReply().get(0));
	}
	
	@Test
	void testGetLike() {
		assertEquals(2, tweet.getLike());
	}
	@Test
	void testGetUsername() {
		assertEquals("user-1", tweet.getUsername());
	}
}
