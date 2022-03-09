package tn.cita.app.exception.wrapper;

public class UsernameAlreadyExistsException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public UsernameAlreadyExistsException() {
		super();
	}
	
	public UsernameAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public UsernameAlreadyExistsException(String message) {
		super(message);
	}
	
	public UsernameAlreadyExistsException(Throwable cause) {
		super(cause);
	}
	
	
	
}















