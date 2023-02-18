package tn.cita.app.exception.wrapper;

public class ReservationAlreadyOutdatedException extends CustomRuntimeException {
	
	private static final long serialVersionUID = 8448905315182968451L;
	
	public ReservationAlreadyOutdatedException() {
		super("Reservation already outdated");
	}
	
	public ReservationAlreadyOutdatedException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ReservationAlreadyOutdatedException(String message) {
		super(message);
	}
	
}











