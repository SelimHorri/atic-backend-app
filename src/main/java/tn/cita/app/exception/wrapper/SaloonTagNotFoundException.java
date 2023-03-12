package tn.cita.app.exception.wrapper;

public class SaloonTagNotFoundException extends BusinessException {
	
	private static final long serialVersionUID = -1948567129776942280L;
	
	public SaloonTagNotFoundException() {
		super("SaloonTag not found");
	}
	
	public SaloonTagNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public SaloonTagNotFoundException(String message) {
		super(message);
	}
	
}







