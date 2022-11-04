package tn.cita.app.exception.wrapper;

public class TaskAlreadyAssignedException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public TaskAlreadyAssignedException() {
		super();
	}
	
	public TaskAlreadyAssignedException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public TaskAlreadyAssignedException(String message) {
		super(message);
	}
	
	public TaskAlreadyAssignedException(Throwable cause) {
		super(cause);
	}
	
	
	
}












