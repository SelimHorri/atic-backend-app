package tn.cita.app.exception.wrapper;

public class OrderedDetailAlreadyExistsException extends BusinessException {
	
	private static final long serialVersionUID = 2672959932252642625L;
	
	public OrderedDetailAlreadyExistsException() {
		super("OrderedDetail already exists");
	}
	
	public OrderedDetailAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public OrderedDetailAlreadyExistsException(String message) {
		super(message);
	}
	
}







