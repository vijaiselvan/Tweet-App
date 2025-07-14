package com.tweetapp.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tweetapp.dto.User;

@ExtendWith(MockitoExtension.class)
class UserTest {

	User user = new User();

	@BeforeEach
	void before() {
		user = new User("123", "user", "2", "vijai@gmail.com", "user2", "9876543211");
	}

	@Test
	void testGetFirstName() {
		assertEquals("user", user.getFirstName());
	}

	@Test
	void testGetLastName() {
		assertEquals("2", user.getLastName());
	}

	@Test
	void testGetEmailId() {
		assertEquals("vijai@gmail.com", user.getEmailId());
	}

	@Test
	void testGetLoginId() {
		assertEquals("123", user.getLoginId());
	}

	@Test
	void testUsername() {
		assertEquals("user2", user.getUsername());
	}

	@Test
	void testContactNumber() {
		assertEquals("9876543211", user.getContactNumber());
	}

}
