package tn.cita.app.exception.wrapper;

import java.io.Serial;

public class UnauthorizedUserException extends BusinessException {
	
	@Serial
	private static final long serialVersionUID = 5774436707657106358L;
	
	public UnauthorizedUserException() {
		super("Unauthorized user");
	}
	
	public UnauthorizedUserException(String message) {
		super(message);
	}
	
}



