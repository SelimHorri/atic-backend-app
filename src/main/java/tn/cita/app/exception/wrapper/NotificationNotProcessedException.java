package tn.cita.app.exception.wrapper;

public class NotificationNotProcessedException extends BusinessException {
	
	private static final long serialVersionUID = -8987832144259422358L;
	
	public NotificationNotProcessedException() {
		super("Mail notification not processed");
	}
	
	public NotificationNotProcessedException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public NotificationNotProcessedException(String message) {
		super(message);
	}
	
}







