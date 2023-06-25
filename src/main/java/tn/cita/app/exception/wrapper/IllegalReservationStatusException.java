package tn.cita.app.exception.wrapper;

import java.io.Serial;

public class IllegalReservationStatusException extends BusinessException {
	
	@Serial
	private static final long serialVersionUID = 1L;
	
	public IllegalReservationStatusException() {
		super("Illegal reservation status");
	}
	
	public IllegalReservationStatusException(String msg) {
		super(msg);
	}
	
}



