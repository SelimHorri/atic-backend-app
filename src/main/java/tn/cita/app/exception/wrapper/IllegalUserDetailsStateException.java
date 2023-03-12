package tn.cita.app.exception.wrapper;

public class IllegalUserDetailsStateException extends BusinessException {
	
	private static final long serialVersionUID = -355404732636991167L;
	
	public IllegalUserDetailsStateException() {
		super("Illegal user details state");
	}
	
	public IllegalUserDetailsStateException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public IllegalUserDetailsStateException(String message) {
		super(message);
	}
	
}







