package tn.cita.app.exception.wrapper;

public class ReservationNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public ReservationNotFoundException() {
		super();
	}
	
	public ReservationNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ReservationNotFoundException(String message) {
		super(message);
	}
	
	public ReservationNotFoundException(Throwable cause) {
		super(cause);
	}
	
	
	
}












