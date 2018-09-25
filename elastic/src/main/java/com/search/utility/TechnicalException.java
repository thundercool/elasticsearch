package com.search.utility;

import org.springframework.http.HttpStatus;

public class TechnicalException extends Exception  {

	private static final long serialVersionUID = 1L;
	
	private String message;

	private HttpStatus errorCode;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(HttpStatus errorCode) {
		this.errorCode = errorCode;
	}

	public TechnicalException(String message, HttpStatus errorCode) {
		super();
		this.message = message;
		this.errorCode = errorCode;
	}

	public TechnicalException() {
		super();
	}

	
	

	
}
