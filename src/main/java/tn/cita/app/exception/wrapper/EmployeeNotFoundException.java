package tn.cita.app.exception.wrapper;

public class EmployeeNotFoundException extends BusinessException {
	
	private static final long serialVersionUID = 2821524102084201685L;
	
	public EmployeeNotFoundException() {
		super("Employee not found");
	}
	
	public EmployeeNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public EmployeeNotFoundException(String message) {
		super(message);
	}
	
}







