package tn.cita.app.exception.wrapper;

public class TaskNotBeganException extends BusinessException {
	
	private static final long serialVersionUID = -75770358696424075L;
	
	public TaskNotBeganException() {
		super("Task not began");
	}
	
	public TaskNotBeganException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public TaskNotBeganException(String message) {
		super(message);
	}
	
}







