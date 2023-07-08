package tn.cita.app.exception.wrapper;

import java.io.Serial;

public class ReservationAlreadyExistsException extends BusinessException {
	
	@Serial
	private static final long serialVersionUID = 6155740968218458681L;
	
	public ReservationAlreadyExistsException() {
		super("Reservation already exists");
	}
	
	public ReservationAlreadyExistsException(String message) {
		super(message);
	}
	
}



