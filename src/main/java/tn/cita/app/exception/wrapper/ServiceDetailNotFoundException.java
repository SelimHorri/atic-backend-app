package tn.cita.app.exception.wrapper;

import java.io.Serial;

public class ServiceDetailNotFoundException extends ObjectNotFoundException {
	
	@Serial
	private static final long serialVersionUID = -7758474423049391283L;
	
	public ServiceDetailNotFoundException() {
		super(ServiceDetailNotFoundException.class);
	}
	
	public ServiceDetailNotFoundException(String message) {
		super(message);
	}
	
}



