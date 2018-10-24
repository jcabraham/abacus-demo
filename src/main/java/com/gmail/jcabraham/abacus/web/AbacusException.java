package com.gmail.jcabraham.abacus.web;

import org.springframework.http.HttpStatus;

public class AbacusException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private HttpStatus status;
	private String message;
	
	public AbacusException(HttpStatus status, String message, Exception e) {
		super(e);
		this.status = status;
		this.message = message;
	}
	
	public HttpStatus getStatus() {
		return status;
	}
	
	public String getMessage() {
		return message;
	}

}
