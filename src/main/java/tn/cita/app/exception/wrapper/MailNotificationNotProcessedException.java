package tn.cita.app.exception.wrapper;

public class MailNotificationNotProcessedException extends CustomRuntimeException {
	
	private static final long serialVersionUID = -8987832144259422358L;
	
	public MailNotificationNotProcessedException() {
		super("Mail notification not processed");
	}
	
	public MailNotificationNotProcessedException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public MailNotificationNotProcessedException(String message) {
		super(message);
	}
	
}







