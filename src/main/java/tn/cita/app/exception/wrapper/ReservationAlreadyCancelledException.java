package tn.cita.app.exception.wrapper;

public class ReservationAlreadyCancelledException extends BusinessException {
	
	private static final long serialVersionUID = 4871665031843891042L;
	
	public ReservationAlreadyCancelledException() {
		super("Reservation already cancelled");
	}
	
	public ReservationAlreadyCancelledException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ReservationAlreadyCancelledException(String message) {
		super(message);
	}
	
}







