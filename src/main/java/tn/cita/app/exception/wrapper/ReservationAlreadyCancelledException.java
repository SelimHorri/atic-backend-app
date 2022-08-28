package tn.cita.app.exception.wrapper;

public class ReservationAlreadyCancelledException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public ReservationAlreadyCancelledException() {
		super();
	}
	
	public ReservationAlreadyCancelledException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ReservationAlreadyCancelledException(String message) {
		super(message);
	}
	
	public ReservationAlreadyCancelledException(Throwable cause) {
		super(cause);
	}
	
	
	
}










