package tn.cita.app.exception.wrapper;

import java.io.Serial;

public class UsernameNotMatchException extends BusinessException {
	
	@Serial
	private static final long serialVersionUID = 715770344457227422L;
	
	public UsernameNotMatchException() {
		super("Username does not match");
	}
	
	public UsernameNotMatchException(String message) {
		super(message);
	}
	
}



