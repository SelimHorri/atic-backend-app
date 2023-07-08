package tn.cita.app.exception.wrapper;

import java.io.Serial;

public class IllegalRoleTypeException extends BusinessException {
	
	@Serial
	private static final long serialVersionUID = 6702405793892562133L;
	
	public IllegalRoleTypeException() {
		super("Illegal registration role type");
	}
	
	public IllegalRoleTypeException(String message) {
		super(message);
	}
	
}



