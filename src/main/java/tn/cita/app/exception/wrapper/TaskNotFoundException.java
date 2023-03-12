package tn.cita.app.exception.wrapper;

public class TaskNotFoundException extends BusinessException {
	
	private static final long serialVersionUID = -3264074807160612232L;
	
	public TaskNotFoundException() {
		super("Task not found");
	}
	
	public TaskNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public TaskNotFoundException(String message) {
		super(message);
	}
	
}







