package tn.cita.app.exception.wrapper;

import java.io.Serial;

public class TagNotFoundException extends ObjectNotFoundException {
	
	@Serial
	private static final long serialVersionUID = 5200220936244874787L;
	
	public TagNotFoundException() {
		super(TagNotFoundException.class);
	}
	
	public TagNotFoundException(String message) {
		super(message);
	}
	
}



