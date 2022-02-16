package tn.cita.app.exception.wrapper;

public class IllegalCredentialsException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public IllegalCredentialsException() {
		super();
	}
	
	public IllegalCredentialsException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public IllegalCredentialsException(String message) {
		super(message);
	}
	
	public IllegalCredentialsException(Throwable cause) {
		super(cause);
	}
	
	
	
}












