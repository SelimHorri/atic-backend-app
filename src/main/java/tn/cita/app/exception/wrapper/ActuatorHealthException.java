package tn.cita.app.exception.wrapper;

import java.io.Serial;

public class ActuatorHealthException extends RuntimeException {
	
	@Serial
	private static final long serialVersionUID = 1L;

	public ActuatorHealthException() {
		super();
	}
	
	public ActuatorHealthException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ActuatorHealthException(String message) {
		super(message);
	}
	
	public ActuatorHealthException(Throwable cause) {
		super(cause);
	}
	
}



