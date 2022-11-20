package tn.cita.app.exception.wrapper;

public class FavouriteAlreadyExistsException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public FavouriteAlreadyExistsException() {
		super();
	}
	
	public FavouriteAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public FavouriteAlreadyExistsException(String message) {
		super(message);
	}
	
	public FavouriteAlreadyExistsException(Throwable cause) {
		super(cause);
	}
	
	
	
}










