package com.tweetapp.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tweetapp.dto.TweetLoginResponse;
import com.tweetapp.dto.User;

@ExtendWith(MockitoExtension.class)
class TweetLoginResponseTest {

	TweetLoginResponse tweetLoginResponse;

	@BeforeEach
	void before() {
		tweetLoginResponse = new TweetLoginResponse("SUCCESS",
				"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMSIsImV4cCI6MTY1OTUzMDU4NiwiaWF0IjoxNjU5NTI4Nzg2fQ.sU616e31QwVi86Dbt_k3MscUUwRY7q1vhLeVc464xEY",
				new User());
	}

	@Test
	void testGetJwt() {
		assertEquals(
				"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMSIsImV4cCI6MTY1OTUzMDU4NiwiaWF0IjoxNjU5NTI4Nzg2fQ.sU616e31QwVi86Dbt_k3MscUUwRY7q1vhLeVc464xEY",
				tweetLoginResponse.getToken());
	}
	@Test
	void testGetStatus() {
		assertEquals("SUCCESS",tweetLoginResponse.getStatus());
	}
	@Test
	void testGetUser() {
//		assertEquals(new User(),tweetLoginResponse.getUser());
	}

}
