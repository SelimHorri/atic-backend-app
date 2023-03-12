package tn.cita.app.exception.wrapper;

public class UnauthorizedUserException extends BusinessException {
	
	private static final long serialVersionUID = 5774436707657106358L;
	
	public UnauthorizedUserException() {
		super("Unauthorized user");
	}
	
	public UnauthorizedUserException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public UnauthorizedUserException(String message) {
		super(message);
	}
	
}







