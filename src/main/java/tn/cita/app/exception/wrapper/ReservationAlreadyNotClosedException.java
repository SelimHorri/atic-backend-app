package tn.cita.app.exception.wrapper;

public class ReservationAlreadyNotClosedException extends CustomRuntimeException {
	
	private static final long serialVersionUID = -4639049784262488983L;
	
	public ReservationAlreadyNotClosedException() {
		super("Reservation already not closed");
	}
	
	public ReservationAlreadyNotClosedException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ReservationAlreadyNotClosedException(String message) {
		super(message);
	}
	
}







