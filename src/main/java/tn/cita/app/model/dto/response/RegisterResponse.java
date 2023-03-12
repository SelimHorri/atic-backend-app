package tn.cita.app.model.dto.response;

import java.io.Serializable;

public record RegisterResponse(Boolean isSuccess, String msg) implements Serializable {
	
	public RegisterResponse(final String msg) {
		this(true, msg);
	}
	
}




