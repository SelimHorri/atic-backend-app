package tn.cita.app.exception.wrapper;

import java.io.Serial;

public class NotificationNotProcessedException extends BusinessException {
	
	@Serial
	private static final long serialVersionUID = -8987832144259422358L;
	
	public NotificationNotProcessedException() {
		super("Mail notification not processed");
	}
	
	public NotificationNotProcessedException(String message) {
		super(message);
	}
	
}



