package com.tweetapp.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TweetLoginRequest {

	@NotNull(message = "USername is mandatory")
	private String username;
	@NotNull(message = "Password is mandatory")
	private String password;

	public TweetLoginRequest() {
	}

	public TweetLoginRequest(@NotNull(message = "Username is mandatory") String username,
			@NotNull(message = "Password is mandatory") String password) {
		super();
		this.username = username;
		this.password = password;
	}

}
