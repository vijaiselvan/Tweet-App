package com.tweetapp.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tweetapp.model.UserRegistration;

@ExtendWith(MockitoExtension.class)
class UserRegistrationTest {

	UserRegistration userRegistration = new UserRegistration();

	@BeforeEach
	void before() {
		userRegistration.setFirstName("user");
		userRegistration.setLastName("2");
		userRegistration.setEmailId("vijai@gmail.com");
		userRegistration.setLoginId("123");
		userRegistration.setUsername("user2");
		userRegistration.setPassword("abc123");
		userRegistration.setConfirmPassword("abc123");
		userRegistration.setContactNumber("9876543211");
	}

	@Test
	void testGetFirstName() {
		assertEquals("user", userRegistration.getFirstName());
	}

	@Test
	void testGetLastName() {
		assertEquals("2", userRegistration.getLastName());
	}

	@Test
	void testGetEmailId() {
		assertEquals("vijai@gmail.com", userRegistration.getEmailId());
	}

	@Test
	void testGetLoginId() {
		assertEquals("123", userRegistration.getLoginId());
	}

	@Test
	void testUsername() {
		assertEquals("user2", userRegistration.getUsername());
	}
	
	@Test
	void testContactNumber() {
		assertEquals("9876543211", userRegistration.getContactNumber());
	}

}
