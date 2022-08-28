package tn.cita.app.exception.wrapper;

public class ReservationAlreadyNotClosedException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public ReservationAlreadyNotClosedException() {
		super();
	}
	
	public ReservationAlreadyNotClosedException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ReservationAlreadyNotClosedException(String message) {
		super(message);
	}
	
	public ReservationAlreadyNotClosedException(Throwable cause) {
		super(cause);
	}
	
	
	
}















