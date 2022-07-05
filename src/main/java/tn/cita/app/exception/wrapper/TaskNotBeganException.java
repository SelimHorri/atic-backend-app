package tn.cita.app.exception.wrapper;

public class TaskNotBeganException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public TaskNotBeganException() {
		super();
	}
	
	public TaskNotBeganException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public TaskNotBeganException(String message) {
		super(message);
	}
	
	public TaskNotBeganException(Throwable cause) {
		super(cause);
	}
	
	
	
}









