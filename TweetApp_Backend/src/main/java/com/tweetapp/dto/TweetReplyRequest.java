package com.tweetapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TweetReplyRequest {

	private int tweetId;
	private String tweetReply;

}
