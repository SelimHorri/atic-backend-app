package tn.cita.app.exception.wrapper;

import java.io.Serial;

public class FavouriteNotFoundException extends ObjectNotFoundException {
	
	@Serial
	private static final long serialVersionUID = 6526680450755325192L;
	
	public FavouriteNotFoundException() {
		super(FavouriteNotFoundException.class);
	}
	
	public FavouriteNotFoundException(String message) {
		super(message);
	}
	
}



