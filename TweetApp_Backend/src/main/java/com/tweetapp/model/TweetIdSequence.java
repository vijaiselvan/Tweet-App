package com.tweetapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "TweetIdSequence")
@Getter
@Setter
public class TweetIdSequence {
	@Id
	private String id;
	private int seq;

}
