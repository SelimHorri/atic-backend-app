package tn.cita.app.dto.notif;

import java.io.Serializable;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public final class MailBodyContentBuilder implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private final String username;
	private final String confirmLink;
	
}












