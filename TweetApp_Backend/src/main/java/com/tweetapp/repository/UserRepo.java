package com.tweetapp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tweetapp.dto.User;

public interface UserRepo extends MongoRepository<User, String> {

	User findByUsername(String username);

	List<User> findByUsernameContaining(String username);

}
