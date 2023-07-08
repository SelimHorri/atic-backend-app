package tn.cita.app.exception.wrapper;

import java.io.Serial;

public class PasswordNotMatchException extends BusinessException {
	
	@Serial
	private static final long serialVersionUID = 8031260988284099874L;
	
	public PasswordNotMatchException() {
		super("Password does not match");
	}
	
	public PasswordNotMatchException(String message) {
		super(message);
	}
	
}



