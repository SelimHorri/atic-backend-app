package tn.cita.app.exception.wrapper;

public class CustomerNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public CustomerNotFoundException() {
		super();
	}
	
	public CustomerNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public CustomerNotFoundException(String message) {
		super(message);
	}
	
	public CustomerNotFoundException(Throwable cause) {
		super(cause);
	}
	
	
	
}












