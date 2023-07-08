package tn.cita.app.exception.wrapper;

import java.io.Serial;

public class CredentialNotFoundException extends ObjectNotFoundException {
	
	@Serial
	private static final long serialVersionUID = -3068886750882968002L;
	
	public CredentialNotFoundException() {
		super(CredentialNotFoundException.class);
	}
	
	public CredentialNotFoundException(String message) {
		super(message);
	}
	
}



