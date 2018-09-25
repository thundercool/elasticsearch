package com.search.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.search.utility.ErrorResponse;
import com.search.utility.TechnicalException;

@ControllerAdvice
public class ErrorResponseHandler {
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorResponse> assertionException(final IllegalArgumentException e) {
		return exceptionHandler(e, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex,final HttpStatus httpStatus) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(httpStatus);
		error.setMessage(ex.getMessage());
		return new ResponseEntity<>(error, httpStatus);
	}
	
	@ExceptionHandler(TechnicalException.class)
	public ResponseEntity<ErrorResponse> handleBussinessException(TechnicalException e){
		return exceptionHandler(e, e.getErrorCode());
	}

}
