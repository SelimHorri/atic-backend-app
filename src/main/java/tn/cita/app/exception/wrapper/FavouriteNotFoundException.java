package tn.cita.app.exception.wrapper;

public class FavouriteNotFoundException extends BusinessException {
	
	private static final long serialVersionUID = 6526680450755325192L;
	
	public FavouriteNotFoundException() {
		super("Favourite not found");
	}
	
	public FavouriteNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public FavouriteNotFoundException(String message) {
		super(message);
	}
	
}







