package tn.cita.app.exception.wrapper;

public class PasswordNotMatchException extends BusinessException {
	
	private static final long serialVersionUID = 8031260988284099874L;
	
	public PasswordNotMatchException() {
		super("Password does not match");
	}
	
	public PasswordNotMatchException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public PasswordNotMatchException(String message) {
		super(message);
	}
	
}







