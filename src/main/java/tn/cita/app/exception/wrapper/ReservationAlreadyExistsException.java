package tn.cita.app.exception.wrapper;

public class ReservationAlreadyExistsException extends BusinessException {
	
	private static final long serialVersionUID = 6155740968218458681L;
	
	public ReservationAlreadyExistsException() {
		super("Reservation already exists");
	}
	
	public ReservationAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ReservationAlreadyExistsException(String message) {
		super(message);
	}
	
}







