package tn.cita.app.exception.wrapper;

import java.io.Serial;

public class OutdatedStartDateReservationException extends BusinessException {
	
	@Serial
	private static final long serialVersionUID = 1615026632578026087L;
	
	public OutdatedStartDateReservationException() {
		super("Outdated startdate reservation");
	}
	
	public OutdatedStartDateReservationException(String message) {
		super(message);
	}
	
}



