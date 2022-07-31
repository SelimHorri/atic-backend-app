package tn.cita.app.exception.wrapper;

public class ReservationAlreadyOutdatedException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public ReservationAlreadyOutdatedException() {
		super();
	}
	
	public ReservationAlreadyOutdatedException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ReservationAlreadyOutdatedException(String message) {
		super(message);
	}
	
	public ReservationAlreadyOutdatedException(Throwable cause) {
		super(cause);
	}
	
	
	
}










