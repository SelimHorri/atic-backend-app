package tn.cita.app.exception.wrapper;

public class OutdatedStartDateReservationException extends BusinessException {
	
	private static final long serialVersionUID = 1615026632578026087L;
	
	public OutdatedStartDateReservationException() {
		super("Outdated startdate reservation");
	}
	
	public OutdatedStartDateReservationException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public OutdatedStartDateReservationException(String message) {
		super(message);
	}
	
}







