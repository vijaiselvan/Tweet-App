package com.tweetapp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.dto.User;
import com.tweetapp.dto.UserForgotPassword;
import com.tweetapp.exception.UserAlreadyExistsException;
import com.tweetapp.exception.UserNotFoundException;
import com.tweetapp.model.UserRegistration;
import com.tweetapp.repository.RegistrationRepository;
import com.tweetapp.repository.UserRepo;

@Service
public class UserAppService {

	private Logger logger = LoggerFactory.getLogger(UserAppService.class);

	@Autowired
	RegistrationRepository registrationRepository;

	@Autowired
	UserRepo userRepo;

	public User addUser(UserRegistration user) {
		User userData;
		if (registrationRepository.findByUsernameOrEmailId(user.getUsername(), user.getEmailId()).isEmpty()
				&& !registrationRepository.existsById(user.getLoginId())) {
			registrationRepository.save(user);
			userData = setUserData(user);
			userRepo.save(userData);
			logger.info("User registered successfully");
		} else {
			throw new UserAlreadyExistsException("User Already exists so you cannot add this user");
		}

		return userData;
	}

	public User setUserData(UserRegistration user) {
		User userData = new User(user.getLoginId(), user.getFirstName(), user.getLastName(), user.getEmailId(),
				user.getUsername(), user.getContactNumber());
		return userData;
	}

	public String updatePassword(UserForgotPassword user) {

		UserRegistration userRegister = registrationRepository.findByUsername(user.getUsername());
		if (user.getUsername().equals(userRegister.getUsername())
				&& !(userRegister.getPassword().equals(user.getNewPassword()))) {
			userRegister.setPassword(user.getNewPassword());
			userRegister.setConfirmPassword(user.getConfirmPassword());
			registrationRepository.save(userRegister);
			return "Password changed successfully";
		} else {
			return "Same password enetered... Password not changed";
		}
	}

	public List<User> getAllUsers() {
		List<User> users = userRepo.findAll();
		if (users != null) {

			return users;
		} else {
			throw new UserNotFoundException("No User found registered to account");
		}
	}

	public List<User> getUserByUsername(String username) {
		List<User> user = userRepo.findByUsernameContaining(username);
		if (user == null) {
			throw new UserNotFoundException("User not found in repository");
		}
		return user;
	}

	public User getUserByUserName(String username) {
		User user = userRepo.findByUsername(username);
		if (user == null) {
			throw new UserNotFoundException("User not found in repository");
		}
		return user;
	}

}
