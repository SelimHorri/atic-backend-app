package tn.cita.app.exception.wrapper;

public class ExpiredVerificationTokenException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public ExpiredVerificationTokenException() {
		super();
	}
	
	public ExpiredVerificationTokenException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ExpiredVerificationTokenException(String message) {
		super(message);
	}
	
	public ExpiredVerificationTokenException(Throwable cause) {
		super(cause);
	}
	
	
	
}














