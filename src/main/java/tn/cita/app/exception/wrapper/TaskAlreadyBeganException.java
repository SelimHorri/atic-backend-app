package tn.cita.app.exception.wrapper;

public class TaskAlreadyBeganException extends BusinessException {
	
	private static final long serialVersionUID = 3055460136107519430L;
	
	public TaskAlreadyBeganException() {
		super("Task already began");
	}
	
	public TaskAlreadyBeganException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public TaskAlreadyBeganException(String message) {
		super(message);
	}
	
}







