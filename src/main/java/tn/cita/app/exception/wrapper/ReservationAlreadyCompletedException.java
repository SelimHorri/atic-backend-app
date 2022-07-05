package tn.cita.app.exception.wrapper;

public class ReservationAlreadyCompletedException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public ReservationAlreadyCompletedException() {
		super();
	}
	
	public ReservationAlreadyCompletedException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ReservationAlreadyCompletedException(String message) {
		super(message);
	}
	
	public ReservationAlreadyCompletedException(Throwable cause) {
		super(cause);
	}
	
	
	
}














