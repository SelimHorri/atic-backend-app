package tn.cita.app.exception.wrapper;

public class VerificationTokenExpiredException extends BusinessException {
	
	private static final long serialVersionUID = -8119221221324770435L;
	
	public VerificationTokenExpiredException() {
		super("Verification token expired");
	}
	
	public VerificationTokenExpiredException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public VerificationTokenExpiredException(String message) {
		super(message);
	}
	
}







