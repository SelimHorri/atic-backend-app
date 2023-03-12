package tn.cita.app.exception.wrapper;

public class TaskAlreadyEndedException extends BusinessException {
	
	private static final long serialVersionUID = -8550100191013534328L;
	
	public TaskAlreadyEndedException() {
		super("Task already ended");
	}
	
	public TaskAlreadyEndedException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public TaskAlreadyEndedException(String message) {
		super(message);
	}
	
}







