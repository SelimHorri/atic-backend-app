package tn.cita.app.exception.wrapper;

import java.io.Serial;

public class FavouriteAlreadyExistsException extends BusinessException {
	
	@Serial
	private static final long serialVersionUID = -7226231704433985429L;
	
	public FavouriteAlreadyExistsException() {
		super("Favourite already exists");
	}
	
	public FavouriteAlreadyExistsException(String message) {
		super(message);
	}
	
	
}



