package com.tweetapp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tweetapp.model.UserRegistration;

public interface RegistrationRepository extends MongoRepository<UserRegistration, String>{

	List<UserRegistration> findByUsernameOrEmailId(String username,String emailId);

	UserRegistration findByUsername(String username);

}
