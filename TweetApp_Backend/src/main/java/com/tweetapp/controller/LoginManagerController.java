package com.tweetapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.dto.TweetLoginRequest;
import com.tweetapp.dto.TweetLoginResponse;
import com.tweetapp.dto.User;
import com.tweetapp.dto.UserForgotPassword;
import com.tweetapp.exception.PasswordMismatchException;
import com.tweetapp.model.UserRegistration;
import com.tweetapp.repository.UserRepo;
import com.tweetapp.service.MyUserDetailsService;
import com.tweetapp.service.UserAppService;
import com.tweetapp.util.JwtTokenUtil;

@CrossOrigin(origins = "http://localhost:3000/")
//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1.0/tweets/")
public class LoginManagerController {
	private Logger logger = LoggerFactory.getLogger(LoginManagerController.class);

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	MyUserDetailsService myUserDetailsService;

	@Autowired
	JwtTokenUtil jwtTokenUtil;

	@Autowired
	UserAppService userAppService;

	@Autowired
	UserRepo userRepo;

	@PostMapping(value = "/login")
	public ResponseEntity<?> authenticate(@RequestBody TweetLoginRequest loginRequest) throws Exception {
		User user = new User();
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}
		final UserDetails userDetails = myUserDetailsService.loadUserByUsername(loginRequest.getUsername());
	
		final String token = jwtTokenUtil.generateToken(userDetails);

		user = userRepo.findByUsername(loginRequest.getUsername());
		if (token == null) {
			return ResponseEntity.ok(new TweetLoginResponse("FAILURE", token, user));
		}
		return ResponseEntity.ok(new TweetLoginResponse("SUCCESS", token, user));

	}

	@PostMapping(value = "/register")
	public ResponseEntity<User> addUser(@RequestBody UserRegistration user) {
		ResponseEntity<User> responseEntity = null;
		if (user.getPassword().equals(user.getConfirmPassword())) {
			User registerUser = userAppService.addUser(user);
			responseEntity = new ResponseEntity<>(registerUser, HttpStatus.CREATED);
		} else {
			logger.warn("User should enter matching password");
			throw new PasswordMismatchException("Password mismatched.. Please enter a matched password");
		}
		return responseEntity;
	}

	@PutMapping(value = "/{username}/forgot")
	public ResponseEntity<String> forgotPassword(@RequestBody UserForgotPassword user) {
		ResponseEntity<String> responseEntity = null;
		String registerUser = null;
		if (user.getNewPassword().equals(user.getConfirmPassword())) {
			registerUser = userAppService.updatePassword(user);
			responseEntity = new ResponseEntity<>(registerUser, HttpStatus.CREATED);
		} else {
			logger.info("User should enter matching password");
			responseEntity = new ResponseEntity<>(registerUser, HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}

	@GetMapping(value = "/health-check")
	public ResponseEntity<String> healthCheck() {
		return new ResponseEntity<>("auth ok", HttpStatus.OK);
	}
}
