package com.tweetapp.dto;

public class TweetLoginResponse {

	private final String token;
	private final String status;
	private final User user;

	public TweetLoginResponse(String status, String token, User user) {
		this.token = token;
		this.status = status;
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public String getStatus() {
		return status;
	}

	public User getUser() {
		return user;
	}
}
