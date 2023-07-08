package tn.cita.app.exception.wrapper;

import java.io.Serial;

public class LocationNotFoundException extends ObjectNotFoundException {
	
	@Serial
	private static final long serialVersionUID = -991158339716479285L;
	
	public LocationNotFoundException() {
		super(LocationNotFoundException.class);
	}
	
	public LocationNotFoundException(String message) {
		super(message);
	}
	
}



