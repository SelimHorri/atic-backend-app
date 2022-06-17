package tn.cita.app.exception.wrapper;

public class TaskAlreadyBegan extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public TaskAlreadyBegan() {
		super();
	}
	
	public TaskAlreadyBegan(String message, Throwable cause) {
		super(message, cause);
	}
	
	public TaskAlreadyBegan(String message) {
		super(message);
	}
	
	public TaskAlreadyBegan(Throwable cause) {
		super(cause);
	}
	
	
	
}










