package tn.cita.app.exception.wrapper;

public class PasswordNotMatchException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public PasswordNotMatchException() {
		super();
	}
	
	public PasswordNotMatchException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public PasswordNotMatchException(String message) {
		super(message);
	}
	
	public PasswordNotMatchException(Throwable cause) {
		super(cause);
	}
	
	
	
}















