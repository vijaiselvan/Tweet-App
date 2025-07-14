package com.tweetapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserForgotPassword {

	private String username;
	private String newPassword;
	private String confirmPassword;

}
