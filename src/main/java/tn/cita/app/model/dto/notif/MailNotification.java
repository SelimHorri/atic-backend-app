package tn.cita.app.model.dto.notif;

import java.io.Serializable;

public record MailNotification(String to, String subject, String body) implements Serializable {
	
	/**
	 * Used when we wanna pass a props as body
	 * @param to
	 * @param subject
	 */
	public MailNotification(String to, String subject) {
		this(to, subject, null);
	}
	
}



