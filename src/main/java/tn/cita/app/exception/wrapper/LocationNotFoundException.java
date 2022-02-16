package tn.cita.app.exception.wrapper;

public class LocationNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public LocationNotFoundException() {
		super();
	}
	
	public LocationNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public LocationNotFoundException(String message) {
		super(message);
	}
	
	public LocationNotFoundException(Throwable cause) {
		super(cause);
	}
	
	
	
}












