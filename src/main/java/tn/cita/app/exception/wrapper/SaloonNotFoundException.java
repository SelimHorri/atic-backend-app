package tn.cita.app.exception.wrapper;

public class SaloonNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public SaloonNotFoundException() {
		super();
	}
	
	public SaloonNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public SaloonNotFoundException(String message) {
		super(message);
	}
	
	public SaloonNotFoundException(Throwable cause) {
		super(cause);
	}
	
	
	
}












