package tn.cita.app.exception.wrapper;

public class EmployeeNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public EmployeeNotFoundException() {
		super();
	}
	
	public EmployeeNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public EmployeeNotFoundException(String message) {
		super(message);
	}
	
	public EmployeeNotFoundException(Throwable cause) {
		super(cause);
	}
	
	
	
}












