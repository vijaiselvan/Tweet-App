package com.tweetapp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.dto.TweetReplyRequest;
import com.tweetapp.dto.TweetUpdateRequest;
import com.tweetapp.exception.TweetAlreadyExistsException;
import com.tweetapp.exception.TweetNotFoundException;
import com.tweetapp.model.Tweet;
import com.tweetapp.repository.RegistrationRepository;
import com.tweetapp.repository.TweetRepo;

@Service
public class TweetAppService {

	private Logger logger = LoggerFactory.getLogger(TweetAppService.class);

	@Autowired
	TweetRepo tweetRepo;

	@Autowired
	RegistrationRepository userRepo;

	@Autowired
	SequenceIdService sequenceIdService;

//	@Autowired
//	KafkaProducerService kafkaProducerService;

	public String addTweet(String username, Tweet tweet) {
		System.out.println("tweet" + tweet.getUsername());
		String user = userRepo.findByUsername(username).getUsername();
		System.out.println(user);
		if (user != null && user.equals(tweet.getUsername())) {
			System.out.println(tweet.getUsername());
			tweet.setTweetReply((tweet.getTweetReply() == null) ? new ArrayList<String>() : tweet.getTweetReply());
			tweet.setCreatedDate(new Date());
			tweet.setTweetId(sequenceIdService.getNextSequence(Tweet.SEQUENCE_NAME));
			tweetRepo.save(tweet);
			logger.info("Tweet added " + tweet);
			// kafkaProducerService.sendMessage("Tweet added");

		} else {
			throw new TweetAlreadyExistsException("Tweet already exists in  " + tweet.getTweetId());
		}
		return "Tweet added Successfully";
	}

	public Tweet updateTweet(TweetUpdateRequest tweetUpdateRequest) {
		logger.info("Tweet update started");
		Tweet tweet = tweetRepo.getTweetByUsernameAndTweetId(tweetUpdateRequest.getUsername(),
				tweetUpdateRequest.getTweetId());
		tweet.setTweet(tweetUpdateRequest.getTweet());
		tweetRepo.save(tweet);
		logger.info("Tweet updated " + tweet);
		return tweet;
	}

	public List<Tweet> getAllTweet() {
		List<Tweet> tweets = tweetRepo.findAll();
		if (tweets != null) {
			logger.info("All tweets fetched");
			// kafkaProducerService.sendMessage("All Tweets retrieved");
			return tweets;
		} else {
			logger.error("No tweets stored in db");
			throw new TweetNotFoundException("No tweets found added by user");
		}

	}

	public List<Tweet> getAllTweetsByUsername(String username) {
		List<Tweet> tweets = tweetRepo.findByUsername(username);
		if (tweets != null) {
			logger.info("Tweets fetched based on username " + username + " tweets are " + tweets);
			// kafkaProducerService.sendMessage("Tweets fetched based on username " +
			// username);
			return tweets;
		} else {
			throw new TweetNotFoundException("No tweets found. User not added any tweet yet");
		}
	}

	public Tweet deleteTweet(String username, Integer id) {
		Tweet tweet = tweetRepo.getTweetByUsernameAndTweetId(username, id);
		if (tweet != null) {
			tweetRepo.delete(tweet);
			logger.info("Tweet deleted ");
			return tweet;
		} else {
			throw new TweetNotFoundException("Tweet not found for tweetId " + id);
		}
	}

	public Tweet likeTweet(String username, Integer id) {
		Tweet tweet = tweetRepo.findByTweetId(id);
		if (tweet != null) {
			tweet.setLike(tweet.getLike() + 1);
			tweetRepo.save(tweet);
			logger.info("Tweet liked by " + username);
			return tweet;
		} else {
			throw new TweetNotFoundException("Tweet not found for tweetId " + id);
		}
	}

	public Tweet replyTweet(String username, Integer id, TweetReplyRequest tweetReplayRequest) {
		Tweet tweet = tweetRepo.findByTweetId(id);
		List<String> tweetReply = tweet.getTweetReply();
		tweetReply.add(tweetReplayRequest.getTweetReply());
		tweet.setTweetReply(tweetReply);
		tweetRepo.save(tweet);
		logger.info("Tweet Replyed by " + username);
		return tweet;
	}

	public Tweet getTweetByTweetId(int tweetId) {
		Tweet tweet = tweetRepo.findByTweetId(tweetId);
		if (tweet != null) {
			logger.info("Tweet retried based on tweet id " + tweetId + " " + tweet);
			return tweet;
		} else {
			throw new TweetNotFoundException("Tweet not found for tweetId " + tweetId);
		}
	}

	public Tweet unlikeTweet(String username, Integer id) {
		Tweet tweet = tweetRepo.findByTweetId(id);
		if (tweet != null) {
			tweet.setLike(tweet.getLike() - 1);
			tweetRepo.save(tweet);
			logger.info("Tweet unliked by " + username);
			return tweet;
		} else {
			throw new TweetNotFoundException("Tweet not found for tweetId " + id);
		}
	}

}
