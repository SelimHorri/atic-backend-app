package tn.cita.app.exception.wrapper;

import java.io.Serial;

public class TaskNotBeganException extends BusinessException {
	
	@Serial
	private static final long serialVersionUID = -75770358696424075L;
	
	public TaskNotBeganException() {
		super("Task not began");
	}
	
	public TaskNotBeganException(String message) {
		super(message);
	}
	
}



