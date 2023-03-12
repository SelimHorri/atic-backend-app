package tn.cita.app.exception.wrapper;

public class CategoryNotFoundException extends BusinessException {
	
	private static final long serialVersionUID = 180299371640031717L;
	
	public CategoryNotFoundException() {
		super("Category not found");
	}
	
	public CategoryNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public CategoryNotFoundException(String message) {
		super(message);
	}
	
}






 

