package tn.cita.app.exception.wrapper;

public class TaskAlreadyBeganException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public TaskAlreadyBeganException() {
		super();
	}
	
	public TaskAlreadyBeganException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public TaskAlreadyBeganException(String message) {
		super(message);
	}
	
	public TaskAlreadyBeganException(Throwable cause) {
		super(cause);
	}
	
	
	
}










