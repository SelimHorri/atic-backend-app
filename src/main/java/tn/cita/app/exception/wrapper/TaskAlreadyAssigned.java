package tn.cita.app.exception.wrapper;

public class TaskAlreadyAssigned extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public TaskAlreadyAssigned() {
		super();
	}
	
	public TaskAlreadyAssigned(String message, Throwable cause) {
		super(message, cause);
	}
	
	public TaskAlreadyAssigned(String message) {
		super(message);
	}
	
	public TaskAlreadyAssigned(Throwable cause) {
		super(cause);
	}
	
	
	
}












