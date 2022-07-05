package tn.cita.app.exception.wrapper;

public class TaskNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public TaskNotFoundException() {
		super();
	}
	
	public TaskNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public TaskNotFoundException(String message) {
		super(message);
	}
	
	public TaskNotFoundException(Throwable cause) {
		super(cause);
	}
	
	
	
}














