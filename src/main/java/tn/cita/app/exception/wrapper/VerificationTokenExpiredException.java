package tn.cita.app.exception.wrapper;

import java.io.Serial;

public class VerificationTokenExpiredException extends BusinessException {
	
	@Serial
	private static final long serialVersionUID = -8119221221324770435L;
	
	public VerificationTokenExpiredException() {
		super("Verification token expired");
	}
	
	public VerificationTokenExpiredException(String message) {
		super(message);
	}
	
}



