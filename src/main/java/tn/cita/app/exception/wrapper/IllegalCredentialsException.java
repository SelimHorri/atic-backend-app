package tn.cita.app.exception.wrapper;

import java.io.Serial;

public class IllegalCredentialsException extends BusinessException {
	
	@Serial
	private static final long serialVersionUID = -841146555237855823L;
	
	public IllegalCredentialsException() {
		super("Illegal credentials");
	}
	
	public IllegalCredentialsException(String message) {
		super(message);
	}
	
}



