package tn.cita.app.exception.wrapper;

public class VerificationTokenNotFoundException extends BusinessException {
	
	private static final long serialVersionUID = -8147403488111373072L;
	
	public VerificationTokenNotFoundException() {
		super("VerificationToken not found");
	}
	
	public VerificationTokenNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public VerificationTokenNotFoundException(String message) {
		super(message);
	}
	
}







