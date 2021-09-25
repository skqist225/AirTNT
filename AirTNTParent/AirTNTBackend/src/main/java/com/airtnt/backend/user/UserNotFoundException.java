package com.airtnt.backend.user;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
		super(message);
	}
}
