package tn.cita.app.exception.wrapper;

public class TaskAlreadyEndedException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public TaskAlreadyEndedException() {
		super();
	}
	
	public TaskAlreadyEndedException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public TaskAlreadyEndedException(String message) {
		super(message);
	}
	
	public TaskAlreadyEndedException(Throwable cause) {
		super(cause);
	}
	
	
	
}











