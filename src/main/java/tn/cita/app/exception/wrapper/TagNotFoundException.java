package tn.cita.app.exception.wrapper;

public class TagNotFoundException extends BusinessException {
	
	private static final long serialVersionUID = 5200220936244874787L;
	
	public TagNotFoundException() {
		super("Tag not found");
	}
	
	public TagNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public TagNotFoundException(String message) {
		super(message);
	}
	
}







