package tn.cita.app.dto.notif;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public final class MailNotification implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private final String to;
	private final String subject;
	private final MailBodyContentBuilder body;
	
}












