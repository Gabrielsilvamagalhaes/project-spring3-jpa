package com.gabriel.course.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(Object id) {
		super("Resource not found | ID: " + id);
	}
	
	public ResourceNotFoundException(String text) {
		super("Resource not found | name: " + text);
	}

}
