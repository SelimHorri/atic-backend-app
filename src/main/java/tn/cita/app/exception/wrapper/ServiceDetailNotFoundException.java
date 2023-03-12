package tn.cita.app.exception.wrapper;

public class ServiceDetailNotFoundException extends BusinessException {
	
	private static final long serialVersionUID = -7758474423049391283L;
	
	public ServiceDetailNotFoundException() {
		super("ServiceDetail not found");
	}
	
	public ServiceDetailNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ServiceDetailNotFoundException(String message) {
		super(message);
	}
	
}







