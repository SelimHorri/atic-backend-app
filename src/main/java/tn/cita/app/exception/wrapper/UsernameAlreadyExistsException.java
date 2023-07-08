package tn.cita.app.exception.wrapper;

import java.io.Serial;

public class UsernameAlreadyExistsException extends BusinessException {
	
	@Serial
	private static final long serialVersionUID = -3147715092199725614L;
	
	public UsernameAlreadyExistsException() {
		super("Username already exists");
	}
	
	public UsernameAlreadyExistsException(String message) {
		super(message);
	}
	
}



