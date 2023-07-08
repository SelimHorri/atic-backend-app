package tn.cita.app.exception.wrapper;

import java.io.Serial;

public class IllegalUserDetailsStateException extends BusinessException {
	
	@Serial
	private static final long serialVersionUID = -355404732636991167L;
	
	public IllegalUserDetailsStateException() {
		super("Illegal user details state");
	}
	
	public IllegalUserDetailsStateException(String message) {
		super(message);
	}
	
}



