package tn.cita.app.exception.wrapper;

public class IllegalRoleTypeException extends BusinessException {
	
	private static final long serialVersionUID = 6702405793892562133L;
	
	public IllegalRoleTypeException() {
		super("Illegal registration role type");
	}
	
	public IllegalRoleTypeException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public IllegalRoleTypeException(String message) {
		super(message);
	}
	
}





