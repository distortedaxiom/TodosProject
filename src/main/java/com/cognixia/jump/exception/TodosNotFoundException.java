package com.cognixia.jump.exception;

public class TodosNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public TodosNotFoundException(int todos_id) {
		super("The todos item by the id that you inputted of " + todos_id + " is not found");
	}
	
}
