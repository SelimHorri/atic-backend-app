package tn.cita.app.exception.wrapper;

import java.io.Serial;

public class EmployeeNotFoundException extends ObjectNotFoundException {
	
	@Serial
	private static final long serialVersionUID = 2821524102084201685L;
	
	public EmployeeNotFoundException() {
		super(EmployeeNotFoundException.class);
	}
	
	public EmployeeNotFoundException(String message) {
		super(message);
	}
	
}



