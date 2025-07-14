package com.tweetapp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;

import com.tweetapp.dto.TweetLoginRequest;
import com.tweetapp.dto.UserForgotPassword;
import com.tweetapp.exception.PasswordMismatchException;
import com.tweetapp.model.UserRegistration;
import com.tweetapp.service.MyUserDetailsService;
import com.tweetapp.service.UserAppService;
import com.tweetapp.util.JwtTokenUtil;

@ExtendWith(MockitoExtension.class)
public class LoginManagerControllerTest {
	
	@InjectMocks
	LoginManagerController loginManagerController;
	
	@Mock
	UserAppService userAppService; 
	
	@Mock
	AuthenticationManager authenticationManager;

	@Mock
	MyUserDetailsService myUserDetailsService;
	
	@Mock
	JwtTokenUtil jwtTokenUtil;
	
	private UserRegistration userRegistration;
	
	@Test
	public void testLogin() throws Exception {
		TweetLoginRequest loginRequest = new TweetLoginRequest("username", "password");
//		assertEquals(200, loginManagerController.authenticate(loginRequest).getStatusCodeValue());
	}
	@Test
	public void testRegisterUser(){
		userRegistration = new UserRegistration();
		userRegistration.setFirstName("user");
		userRegistration.setLastName("2");
		userRegistration.setEmailId("vijai@gmail.com");
		userRegistration.setLoginId("123");
		userRegistration.setUsername("user2");
		userRegistration.setPassword("abc123");
		userRegistration.setConfirmPassword("abc123");
		userRegistration.setContactNumber("9876543211");
//		Mockito.lenient().when(userAppService.addUser(userRegistration)).thenReturn(userAppService.setUserData(userRegistration));
//		assertEquals(userAppService.setUserData(userRegistration), loginManagerController.addUser(userRegistration));
	}
	
	@Test
	public void testRegisterUserException() throws Exception {
		userRegistration = new UserRegistration();
		userRegistration.setFirstName("user");
		userRegistration.setLastName("2");
		userRegistration.setEmailId("vijai@gmail.com");
		userRegistration.setLoginId("123");
		userRegistration.setUsername("user2");
		userRegistration.setPassword("abc123");
		userRegistration.setConfirmPassword("abc");
		userRegistration.setContactNumber("9876543211");
		assertThrows(PasswordMismatchException.class, ()->{loginManagerController.addUser(userRegistration);});
	}
	
	@Test
	public void forgotPasswordSuccess() {
		UserForgotPassword forgotPassword=new UserForgotPassword();
		forgotPassword.setUsername("test-user1");
		forgotPassword.setNewPassword("test-pass");
		forgotPassword.setConfirmPassword("test-pass");
		assertEquals(201, loginManagerController.forgotPassword(forgotPassword).getStatusCodeValue());
	}
	
	@Test
	public void forgotPasswordFail() {
		UserForgotPassword forgotPassword=new UserForgotPassword();
		forgotPassword.setUsername("test-user1");
		forgotPassword.setNewPassword("test-pass");
		forgotPassword.setConfirmPassword("test");
		assertEquals(400, loginManagerController.forgotPassword(forgotPassword).getStatusCodeValue());
	}
	
	@Test
	void testHealthCheck() {
		assertEquals(HttpStatus.OK,loginManagerController.healthCheck().getStatusCode());
	}
}
