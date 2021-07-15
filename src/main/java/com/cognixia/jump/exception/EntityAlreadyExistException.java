package com.cognixia.jump.exception;

public class EntityAlreadyExistException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public EntityAlreadyExistException(String username) {
		super("The username " + username + " already exists");
	}
}
