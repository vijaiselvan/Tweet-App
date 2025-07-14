package com.tweetapp.exception;

public class PasswordMismatchException extends RuntimeException {

	/**
	 * Password Mismatch Exception
	 */
	private static final long serialVersionUID = 1L;

	public PasswordMismatchException(String msg) {
		super(msg);
	}

}
