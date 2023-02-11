package tn.cita.app.exception.wrapper;

public class TaskAlreadyAssignedException extends CustomRuntimeException {
	
	private static final long serialVersionUID = -1249056480757914103L;
	
	public TaskAlreadyAssignedException() {
		super("Task already assigned");
	}
	
	public TaskAlreadyAssignedException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public TaskAlreadyAssignedException(String message) {
		super(message);
	}
	
}







