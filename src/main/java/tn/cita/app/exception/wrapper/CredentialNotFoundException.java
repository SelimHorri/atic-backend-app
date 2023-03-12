package tn.cita.app.exception.wrapper;

public class CredentialNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -3068886750882968002L;
	
	public CredentialNotFoundException() {
		super("Credential not found");
	}
	
	public CredentialNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public CredentialNotFoundException(String message) {
		super(message);
	}
	
}








