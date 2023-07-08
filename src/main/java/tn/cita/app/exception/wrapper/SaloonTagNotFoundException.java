package tn.cita.app.exception.wrapper;

import java.io.Serial;

public class SaloonTagNotFoundException extends ObjectNotFoundException {
	
	@Serial
	private static final long serialVersionUID = -1948567129776942280L;
	
	public SaloonTagNotFoundException() {
		super(SaloonTagNotFoundException.class);
	}
	
	public SaloonTagNotFoundException(String message) {
		super(message);
	}
	
}



