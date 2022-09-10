package com.training.mrp.exception;

public class DataMissingException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public DataMissingException(String message) {
		super(message);
	}
}