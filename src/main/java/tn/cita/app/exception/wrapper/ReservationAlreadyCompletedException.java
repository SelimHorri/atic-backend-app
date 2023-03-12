package tn.cita.app.exception.wrapper;

public class ReservationAlreadyCompletedException extends BusinessException {
	
	private static final long serialVersionUID = -9029362194361139901L;
	
	public ReservationAlreadyCompletedException() {
		super("Reservation already completed");
	}
	
	public ReservationAlreadyCompletedException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ReservationAlreadyCompletedException(String message) {
		super(message);
	}
	
}







