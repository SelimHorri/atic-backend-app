package tn.cita.app.exception.wrapper;

public class ActuatorHealthException extends RuntimeException {
	
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




