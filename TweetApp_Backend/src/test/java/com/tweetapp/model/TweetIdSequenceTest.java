package com.tweetapp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tweetapp.model.TweetIdSequence;

@ExtendWith(MockitoExtension.class)
class TweetIdSequenceTest {
	TweetIdSequence tweetIdSequence = new TweetIdSequence();

	@BeforeEach
	void before() {
		tweetIdSequence.setId("123");
		tweetIdSequence.setSeq(1);
	}

	@Test
	void testGetSeq() {
		assertEquals(1, tweetIdSequence.getSeq());
	}

	@Test
	void testGetId() {
		assertEquals("123", tweetIdSequence.getId());
	}

}
