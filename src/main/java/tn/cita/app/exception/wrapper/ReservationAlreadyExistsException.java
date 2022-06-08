package tn.cita.app.exception.wrapper;

public class ReservationAlreadyExistsException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public ReservationAlreadyExistsException() {
		super();
	}
	
	public ReservationAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ReservationAlreadyExistsException(String message) {
		super(message);
	}
	
	public ReservationAlreadyExistsException(Throwable cause) {
		super(cause);
	}
	
	
	
}
















