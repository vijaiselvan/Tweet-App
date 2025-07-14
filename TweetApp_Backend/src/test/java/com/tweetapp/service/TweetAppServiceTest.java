package com.tweetapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tweetapp.dto.TweetReplyRequest;
import com.tweetapp.dto.TweetUpdateRequest;
import com.tweetapp.dto.User;
import com.tweetapp.exception.TweetAlreadyExistsException;
import com.tweetapp.exception.TweetNotFoundException;
import com.tweetapp.model.Tweet;
import com.tweetapp.model.UserRegistration;
import com.tweetapp.repository.RegistrationRepository;
import com.tweetapp.repository.TweetRepo;
import com.tweetapp.repository.UserRepo;
import com.tweetapp.service.TweetAppService;

@ExtendWith(MockitoExtension.class)
class TweetAppServiceTest {

	@InjectMocks
	TweetAppService tweetAppService;
	@Mock
	TweetRepo tweetRepo;
	@Mock
	RegistrationRepository userRegisterRepo;
	@Mock
	UserRepo userRepo;
//	@Mock
//	KafkaProducerService kafkaProducerService;
	private User user;
	private UserRegistration userRegistration;
	private Tweet tweet;
	private Tweet tweet1;

	@BeforeEach
	void before() {
		userRegistration = new UserRegistration();
		userRegistration.setFirstName("user");
		userRegistration.setLastName("2");
		userRegistration.setEmailId("vijai@gmail.com");
		userRegistration.setLoginId("123");
		userRegistration.setUsername("user2");
		userRegistration.setPassword("abc123");
		userRegistration.setConfirmPassword("abc123");
		userRegistration.setContactNumber("9876543211");
		user = new User();
		user.setFirstName("user");
		user.setLastName("2");
		user.setLoginId("123");
		user.setUsername("user2");
		user.setEmailId("vijai@gmail.com");
		user.setContactNumber("9876543211");
		tweet = new Tweet();
		tweet.setTweetId(101);
		tweet.setTweet("test-tweet");
		tweet.setLike(1);
		tweet.setTweetTag("tag-tweet");
		tweet.setTweetReply(new ArrayList<String>());
		tweet.setUsername("user1");
		tweet1 = new Tweet();
		tweet1.setUsername("test-u");
		tweet1.setTweetId(1001);
		tweet1.setLike(1);
		tweet1.setTweet("test-tweet");
		tweet1.setTweetTag("tag");
	}

	@Test
	public void testAddTweetSuccess() {
		Mockito.lenient().when(userRepo.findByUsername(ArgumentMatchers.anyString())).thenReturn(user);
		Mockito.lenient().when(tweetRepo.save(tweet)).thenReturn(tweet);
//		assertEquals("Tweet added Successfully", tweetAppService.addTweet("user1", tweet));
		Mockito.lenient().when(userRepo.findByUsername("user1")).thenThrow(TweetAlreadyExistsException.class);
//		assertThrows(TweetAlreadyExistsException.class, () -> tweetAppService.addTweet("user2", tweet));
	}

	@Test
	void testGetAllTweetSuccess() {
		List<Tweet> getAllTweet = new ArrayList<>();
		getAllTweet.add(tweet);
		Tweet tweet1 = new Tweet();
		tweet1.setTweetId(102);
		tweet1.setTweet("test-tweet");
		tweet1.setLike(2);
		tweet1.setTweetTag("tag-tweet");
		tweet1.setTweetReply(new ArrayList<String>());
		tweet1.setUsername("user2");
		getAllTweet.add(tweet1);
		List<Tweet> listTweet = new ArrayList<Tweet>();
		listTweet.add(tweet);
		when(tweetRepo.findAll()).thenReturn(listTweet);
		assertEquals(tweet.getUsername(), tweetAppService.getAllTweet().get(0).getUsername());
		tweetRepo.deleteAll();
//		assertThrows(TweetNotFoundException.class, () -> tweetAppService.getAllTweet());
	}

	@Test
	public void testGetTweetByUsername() {
		List<Tweet> listTweet = new ArrayList<Tweet>();
		listTweet.add(tweet);
		when(tweetRepo.findByUsername(ArgumentMatchers.anyString())).thenReturn(listTweet);
		assertEquals(tweet.getTweetId(),
				tweetAppService.getAllTweetsByUsername(tweet.getUsername()).get(0).getTweetId());
//		assertThrows(TweetNotFoundException.class, () -> tweetAppService.getAllTweetsByUsername("u11"));

	}

	@Test
	public void testGetAllTweetByTweetId() {
		List<Tweet> listTweet = new ArrayList<Tweet>();
		listTweet.add(tweet);
		Mockito.lenient().when(tweetRepo.findByUsername(ArgumentMatchers.anyString())).thenReturn(listTweet);
//		assertEquals(tweet.getUsername(), tweetAppService.getTweetByTweetId(tweet.getTweetId()).getUsername());
		assertThrows(TweetNotFoundException.class, () -> tweetAppService.getTweetByTweetId(1));

	}

	@Test
	public void updateTweetSuccess() {
		TweetUpdateRequest updateTweetRequest = new TweetUpdateRequest();
		updateTweetRequest.setTweetId(tweet.getTweetId());
		updateTweetRequest.setUsername(tweet.getUsername());
		updateTweetRequest.setTweet(tweet.getTweet());
		when(tweetRepo.getTweetByUsernameAndTweetId(updateTweetRequest.getUsername(), updateTweetRequest.getTweetId()))
				.thenReturn(tweet);
		when(tweetRepo.save(ArgumentMatchers.any())).thenReturn(tweet);
		assertEquals(tweet.getLike(), tweetAppService.updateTweet(updateTweetRequest).getLike());
	}

	@Test
	public void testDeleteSuccess() {
		when(tweetRepo.getTweetByUsernameAndTweetId(tweet.getUsername(), tweet.getTweetId())).thenReturn(tweet);
		assertNotNull(tweetAppService.deleteTweet(tweet.getUsername(), tweet.getTweetId()));
		assertThrows(TweetNotFoundException.class, () -> tweetAppService.deleteTweet(tweet.getUsername(), null));
	}

	@Test
	void testLikeTweet() {
		when(tweetRepo.findByTweetId(ArgumentMatchers.anyInt())).thenReturn(tweet);
		when(tweetRepo.save(ArgumentMatchers.any())).thenReturn(tweet);
		assertEquals(tweet.getTweetReply(),
				tweetAppService.likeTweet(tweet.getUsername(), tweet.getTweetId()).getTweetReply());
		assertThrows(TweetNotFoundException.class, () -> tweetAppService.likeTweet(tweet.getUsername(), null));
	}

	@Test
	void testReply() {
		TweetReplyRequest replyRequest = new TweetReplyRequest();
		replyRequest.setTweetId(tweet.getTweetId());
		replyRequest.setTweetReply("tweet");
		when(tweetRepo.findByTweetId(ArgumentMatchers.anyInt())).thenReturn(tweet);
		when(tweetRepo.save(ArgumentMatchers.any())).thenReturn(tweet);
		assertEquals(tweet.getLike(),
				tweetAppService.replyTweet(tweet.getUsername(), tweet.getTweetId(), replyRequest).getLike());
	}

}
