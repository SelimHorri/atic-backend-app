package tn.cita.app.exception.wrapper;

import java.io.Serial;

public class TaskNotFoundException extends ObjectNotFoundException {
	
	@Serial
	private static final long serialVersionUID = -3264074807160612232L;
	
	public TaskNotFoundException() {
		super(TaskNotFoundException.class);
	}
	
	public TaskNotFoundException(String message) {
		super(message);
	}
	
}



