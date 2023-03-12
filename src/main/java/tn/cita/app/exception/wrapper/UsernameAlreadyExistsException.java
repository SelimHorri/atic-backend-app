package tn.cita.app.exception.wrapper;

public class UsernameAlreadyExistsException extends BusinessException {
	
	private static final long serialVersionUID = -3147715092199725614L;
	
	public UsernameAlreadyExistsException() {
		super("Username already exists");
	}
	
	public UsernameAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public UsernameAlreadyExistsException(String message) {
		super(message);
	}
	
}







