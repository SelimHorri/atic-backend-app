package tn.cita.app.exception.wrapper;

public class IllegalCredentialsException extends BusinessException {
	
	private static final long serialVersionUID = -841146555237855823L;
	
	public IllegalCredentialsException() {
		super("Illegal credentials");
	}
	
	public IllegalCredentialsException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public IllegalCredentialsException(String message) {
		super(message);
	}
	
}







