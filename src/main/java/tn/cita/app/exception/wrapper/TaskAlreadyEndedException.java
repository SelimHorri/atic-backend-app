package tn.cita.app.exception.wrapper;

import java.io.Serial;

public class TaskAlreadyEndedException extends BusinessException {
	
	@Serial
	private static final long serialVersionUID = -8550100191013534328L;
	
	public TaskAlreadyEndedException() {
		super("Task already ended");
	}
	
	public TaskAlreadyEndedException(String message) {
		super(message);
	}
	
}



