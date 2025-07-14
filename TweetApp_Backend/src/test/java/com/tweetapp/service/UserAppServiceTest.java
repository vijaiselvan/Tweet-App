package com.tweetapp.service;

import static org.junit.jupiter.api.Assertions.*;
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

import com.tweetapp.dto.User;
import com.tweetapp.dto.UserForgotPassword;
import com.tweetapp.exception.UserAlreadyExistsException;
import com.tweetapp.model.Tweet;
import com.tweetapp.model.UserRegistration;
import com.tweetapp.repository.RegistrationRepository;
import com.tweetapp.repository.TweetRepo;
import com.tweetapp.repository.UserRepo;
import com.tweetapp.service.UserAppService;

@ExtendWith(MockitoExtension.class)
class UserAppServiceTest {

	@InjectMocks
	UserAppService userAppService;
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
	}

	@Test
	public void testAddUserSuccess() throws Exception {
//		when(userRegisterRepo.findByUsernameOrEmailId("user2", "vijai@gmail.com")).thenReturn(new ArrayList<>());
		Mockito.lenient().when(userRegisterRepo.save(userRegistration)).thenReturn(null);
		Mockito.lenient().when(userRepo.save(ArgumentMatchers.any())).thenReturn(user);
//		assertEquals("User Already exists so you cannot add this user", userAppService.addUser(userRegistration));

	}
	
	@Test
	public void testGetAllUsers() {
		List<User> listUser = new ArrayList<>();
		listUser.add(user);
		when(userRepo.findAll()).thenReturn(listUser);
		assertEquals(user.getContactNumber(), userAppService.getAllUsers().get(0).getContactNumber());
//		assertThrows(UserAlreadyExistsException.class, () -> userAppService.getAllUsers());
	}

	@Test
	public void testAddUser() throws Exception {
		List<UserRegistration> listReg = new ArrayList<>();
		listReg.add(userRegistration);
		listReg.add(new UserRegistration());
		when(userRegisterRepo.findByUsernameOrEmailId(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
				.thenReturn(listReg);
		assertThrows(UserAlreadyExistsException.class, () -> userAppService.addUser(userRegistration));
	}

	@Test
	public void testForgotPassword() {
		UserForgotPassword forgotPassword = new UserForgotPassword();
		forgotPassword.setUsername(userRegistration.getUsername());
		forgotPassword.setNewPassword(userRegistration.getPassword());
		forgotPassword.setConfirmPassword(userRegistration.getConfirmPassword());
		when(userRegisterRepo.findByUsername(ArgumentMatchers.anyString())).thenReturn(userRegistration);
		assertNotNull(userAppService.updatePassword(forgotPassword));
	}

	@Test
	public void testGetUserByUseraame() {
//		when(userRepo.findByUsername("user2")).thenReturn(user);
		assertEquals(userRepo.findByUsernameContaining(user.getUsername()), userAppService.getUserByUsername(user.getUsername()));
	}

}
