package com.tweetapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TweetUpdateRequest {

	private String username;
	private Integer tweetId;
	private String tweet;

}
