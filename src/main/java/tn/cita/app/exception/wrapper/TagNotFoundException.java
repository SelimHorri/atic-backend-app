package tn.cita.app.exception.wrapper;

public class TagNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public TagNotFoundException() {
		super();
	}
	
	public TagNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public TagNotFoundException(String message) {
		super(message);
	}
	
	public TagNotFoundException(Throwable cause) {
		super(cause);
	}
	
	
	
}












