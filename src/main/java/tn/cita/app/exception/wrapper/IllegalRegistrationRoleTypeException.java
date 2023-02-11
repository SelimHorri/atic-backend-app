package tn.cita.app.exception.wrapper;

public class IllegalRegistrationRoleTypeException extends CustomRuntimeException {
	
	private static final long serialVersionUID = 6702405793892562133L;
	
	public IllegalRegistrationRoleTypeException() {
		super("Illegal registration role type");
	}
	
	public IllegalRegistrationRoleTypeException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public IllegalRegistrationRoleTypeException(String message) {
		super(message);
	}
	
}





