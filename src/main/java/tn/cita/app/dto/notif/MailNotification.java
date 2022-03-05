package tn.cita.app.dto.notif;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public final class MailNotification implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String from;
	private String to;
	private String subject;
	private String body;
	
}












