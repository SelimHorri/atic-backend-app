package tn.cita.app.exception.wrapper;

import java.io.Serial;

public class CustomerNotFoundException extends ObjectNotFoundException {
	
	@Serial
	private static final long serialVersionUID = -2515356372938824307L;
	
	public CustomerNotFoundException() {
		super(CustomerNotFoundException.class);
	}
	
	public CustomerNotFoundException(String message) {
		super(message);
	}
	
}



