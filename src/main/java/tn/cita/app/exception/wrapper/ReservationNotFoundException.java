package tn.cita.app.exception.wrapper;

import java.io.Serial;

public class ReservationNotFoundException extends ObjectNotFoundException {
	
	@Serial
	private static final long serialVersionUID = 619294856688367853L;
	
	public ReservationNotFoundException() {
		super(ReservationNotFoundException.class);
	}
	
	public ReservationNotFoundException(String message) {
		super(message);
	}
	
}



