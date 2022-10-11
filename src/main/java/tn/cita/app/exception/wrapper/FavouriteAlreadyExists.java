package tn.cita.app.exception.wrapper;

public class FavouriteAlreadyExists extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public FavouriteAlreadyExists() {
		super();
	}
	
	public FavouriteAlreadyExists(String message, Throwable cause) {
		super(message, cause);
	}
	
	public FavouriteAlreadyExists(String message) {
		super(message);
	}
	
	public FavouriteAlreadyExists(Throwable cause) {
		super(cause);
	}
	
	
	
}










