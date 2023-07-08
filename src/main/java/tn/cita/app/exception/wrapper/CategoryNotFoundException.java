package tn.cita.app.exception.wrapper;

import java.io.Serial;

public class CategoryNotFoundException extends ObjectNotFoundException {
	
	@Serial
	private static final long serialVersionUID = 180299371640031717L;
	
	public CategoryNotFoundException() {
		super(CategoryNotFoundException.class);
	}
	
	public CategoryNotFoundException(String message) {
		super(message);
	}
	
}



