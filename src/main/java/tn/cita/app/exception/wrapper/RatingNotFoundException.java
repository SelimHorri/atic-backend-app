package tn.cita.app.exception.wrapper;

public class RatingNotFoundException extends BusinessException {
	
	private static final long serialVersionUID = -1556209975174886493L;
	
	public RatingNotFoundException() {
		super("Rating not found");
	}
	
	public RatingNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public RatingNotFoundException(String message) {
		super(message);
	}
	
}







