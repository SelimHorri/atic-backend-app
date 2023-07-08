package tn.cita.app.exception.wrapper;

import java.io.Serial;

public class TaskAlreadyAssignedException extends BusinessException {
	
	@Serial
	private static final long serialVersionUID = -1249056480757914103L;
	
	public TaskAlreadyAssignedException() {
		super("Task is already assigned");
	}
	
	public TaskAlreadyAssignedException(String message) {
		super(message);
	}
	
}



