package tn.cita.app.exception.wrapper;

import java.io.Serial;

public class TaskAlreadyBeganException extends BusinessException {
	
	@Serial
	private static final long serialVersionUID = 3055460136107519430L;
	
	public TaskAlreadyBeganException() {
		super("Task already began");
	}
	
	public TaskAlreadyBeganException(String message) {
		super(message);
	}
	
}



