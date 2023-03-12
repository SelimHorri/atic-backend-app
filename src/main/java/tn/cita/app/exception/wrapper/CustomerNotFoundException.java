package tn.cita.app.exception.wrapper;

public class CustomerNotFoundException extends BusinessException {
	
	private static final long serialVersionUID = -2515356372938824307L;
	
	public CustomerNotFoundException() {
		super("Customer not found");
	}
	
	public CustomerNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public CustomerNotFoundException(String message) {
		super(message);
	}
	
}







