package com.tweetapp;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import com.tweetapp.TweetappApplication;

@SpringBootTest
class TweetappApplicationTests {
	
	@InjectMocks 
	TweetappApplication tweetappApplication;

	@Test
	void contextLoads() {
		String[] args= {"arg1","arg2"};
		TweetappApplication.main(args);
	}

}
