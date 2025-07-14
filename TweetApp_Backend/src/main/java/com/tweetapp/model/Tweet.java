package com.tweetapp.model;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document
@Getter
@Setter
public class Tweet {
	@Transient
	public static final String SEQUENCE_NAME = "tweets_sequence";
	@Id
	private int tweetId;

	@Size(max = 50, message = "TweetTag is optional")
	private String tweetTag;

	@Size(max = 144, message = "Tweet must be mandatory")
	private String tweet;

	private int like;

	@CreatedDate
	private Date createdDate;

	@NotNull(message = "UserName must be mandatory")
	private String username;

	private List<String> tweetReply;
}
