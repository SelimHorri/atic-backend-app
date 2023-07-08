package tn.cita.app.exception.wrapper;

import java.io.Serial;

public class SaloonNotFoundException extends ObjectNotFoundException {
	
	@Serial
	private static final long serialVersionUID = 2010044366647954585L;
	
	public SaloonNotFoundException() {
		super(SaloonNotFoundException.class);
	}
	
	public SaloonNotFoundException(String message) {
		super(message);
	}
	
}



