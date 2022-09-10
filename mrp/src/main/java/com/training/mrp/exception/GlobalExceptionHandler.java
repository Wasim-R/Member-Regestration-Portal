package com.training.mrp.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException exception, WebRequest request ){
		CustomErrorEntity errorDetails = new CustomErrorEntity(new Date(), exception.getMessage(), 404, "NOT_FOUND", request.getDescription(true));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DataMissingException.class)
	public ResponseEntity<?> dataMissingException(DataMissingException exception, WebRequest request){
		CustomErrorEntity errorDetails = new CustomErrorEntity(new Date(), exception.getMessage(), 400, "BAD_REQUEST", request.getDescription(true));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ClaimAlreadyExistException.class)
	public ResponseEntity<?> claimAlreadyExistException(ClaimAlreadyExistException exception, WebRequest request){
		CustomErrorEntity errorDetails = new CustomErrorEntity(new Date(), exception.getMessage(), 208, "ALREADY_EXIST", request.getDescription(true));
		return new ResponseEntity<>(errorDetails, HttpStatus.ALREADY_REPORTED);
	}
	
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<?> otherException(Exception exception, WebRequest request){
//		CustomErrorEntity errorDetails = 
//				new CustomErrorEntity(new Date(), exception.getMessage(), 500, "INTERNAL SERVER ERROR", request.getDescription(false));
//		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
//	}
}
