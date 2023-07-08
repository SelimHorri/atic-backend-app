package tn.cita.app.exception.wrapper;

import java.io.Serial;

public class RatingNotFoundException extends ObjectNotFoundException {
	
	@Serial
	private static final long serialVersionUID = -1556209975174886493L;
	
	public RatingNotFoundException() {
		super(RatingNotFoundException.class);
	}
	
	public RatingNotFoundException(String message) {
		super(message);
	}
	
}



