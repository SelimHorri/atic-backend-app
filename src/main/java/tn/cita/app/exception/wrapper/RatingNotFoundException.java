package tn.cita.app.exception.wrapper;

public class RatingNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public RatingNotFoundException() {
		super();
	}
	
	public RatingNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public RatingNotFoundException(String message) {
		super(message);
	}
	
	public RatingNotFoundException(Throwable cause) {
		super(cause);
	}
	
	
	
}












