package tn.cita.app.business.auth.register.model;

import java.io.Serializable;

public record RegisterResponse(Boolean isSuccess, String msg) implements Serializable {
	
	public RegisterResponse(final String msg) {
		this(true, msg);
	}
	
}




