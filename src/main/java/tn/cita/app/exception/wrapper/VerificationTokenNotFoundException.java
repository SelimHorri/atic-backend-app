package tn.cita.app.exception.wrapper;

import java.io.Serial;

public class VerificationTokenNotFoundException extends ObjectNotFoundException {
	
	@Serial
	private static final long serialVersionUID = -8147403488111373072L;
	
	public VerificationTokenNotFoundException() {
		super(VerificationTokenNotFoundException.class);
	}
	
	public VerificationTokenNotFoundException(String message) {
		super(message);
	}
	
}



