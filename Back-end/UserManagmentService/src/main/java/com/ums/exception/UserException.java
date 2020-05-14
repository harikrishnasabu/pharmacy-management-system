package com.ums.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class UserException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8342543707146278464L;
	private String message;
	
	public UserException(String message) {
		this.message = message; 
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
