package tn.cita.app.exception.wrapper;

public class ReservationNotFoundException extends BusinessException {
	
	private static final long serialVersionUID = 619294856688367853L;
	
	public ReservationNotFoundException() {
		super("Reservation not found");
	}
	
	public ReservationNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ReservationNotFoundException(String message) {
		super(message);
	}
	
}







