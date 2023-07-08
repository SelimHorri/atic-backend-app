package tn.cita.app.exception.wrapper;

import java.io.Serial;

public class AccessTokenExpiredException extends RuntimeException {
	
	@Serial
	private static final long serialVersionUID = 1L;
	
	public AccessTokenExpiredException() {
		super();
	}
	
	public AccessTokenExpiredException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public AccessTokenExpiredException(String message) {
		super(message);
	}
	
	public AccessTokenExpiredException(Throwable cause) {
		super(cause);
	}
	
	
	
}



