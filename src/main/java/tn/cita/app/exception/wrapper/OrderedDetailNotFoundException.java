package tn.cita.app.exception.wrapper;

public class OrderedDetailNotFoundException extends BusinessException {
	
	private static final long serialVersionUID = -6877038970731972594L;
	
	public OrderedDetailNotFoundException() {
		super("OrderedDetail not found");
	}
	
	public OrderedDetailNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public OrderedDetailNotFoundException(String message) {
		super(message);
	}
	
}







