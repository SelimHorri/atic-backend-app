package tn.cita.app.exception.wrapper;

public class LocationNotFoundException extends BusinessException {
	
	private static final long serialVersionUID = -991158339716479285L;
	
	public LocationNotFoundException() {
		super("Location not found");
	}
	
	public LocationNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public LocationNotFoundException(String message) {
		super(message);
	}
	
}







