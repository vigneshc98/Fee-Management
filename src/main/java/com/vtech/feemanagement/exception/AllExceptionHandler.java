package com.vtech.feemanagement.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AllExceptionHandler {
	
	@Autowired
	private ResourceErrorResponse errorResponse;
	
	@ExceptionHandler
	public ResponseEntity<ResourceErrorResponse> handleExcetion(ResourceNotFoundException exc){
		errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
		errorResponse.setMessage(exc.getMessage());
		errorResponse.setTimestamp(System.currentTimeMillis());
		
		return new ResponseEntity<ResourceErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);	
	}
	
	@ExceptionHandler
	public ResponseEntity<ResourceErrorResponse> handleAllException(Exception exc) {
		
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResponse.setMessage(exc.getMessage());
		errorResponse.setTimestamp(System.currentTimeMillis());
		
		return new ResponseEntity<ResourceErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);	
	}


}
