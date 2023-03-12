package tn.cita.app.exception.wrapper;

public class SaloonNotFoundException extends BusinessException {
	
	private static final long serialVersionUID = 2010044366647954585L;
	
	public SaloonNotFoundException() {
		super("Saloon not found");
	}
	
	public SaloonNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public SaloonNotFoundException(String message) {
		super(message);
	}
	
}







