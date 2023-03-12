package tn.cita.app.exception.wrapper;

public class UsernameNotMatchException extends BusinessException {
	
	private static final long serialVersionUID = 715770344457227422L;
	
	public UsernameNotMatchException() {
		super("Username does not match");
	}
	
	public UsernameNotMatchException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public UsernameNotMatchException(String message) {
		super(message);
	}
	
}







