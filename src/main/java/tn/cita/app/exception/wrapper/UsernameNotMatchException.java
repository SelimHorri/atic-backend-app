package tn.cita.app.exception.wrapper;

public class UsernameNotMatchException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public UsernameNotMatchException() {
		super();
	}
	
	public UsernameNotMatchException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public UsernameNotMatchException(String message) {
		super(message);
	}
	
	public UsernameNotMatchException(Throwable cause) {
		super(cause);
	}
	
	
	
}













