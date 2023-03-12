package tn.cita.app.exception.wrapper;

public class FavouriteAlreadyExistsException extends BusinessException {
	
	private static final long serialVersionUID = -7226231704433985429L;
	
	public FavouriteAlreadyExistsException() {
		super("Favourite already exists");
	}
	
	public FavouriteAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public FavouriteAlreadyExistsException(String message) {
		super(message);
	}
	
	
}







