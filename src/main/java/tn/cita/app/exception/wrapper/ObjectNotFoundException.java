package tn.cita.app.exception.wrapper;

import java.io.Serial;

public class ObjectNotFoundException extends BusinessException {
	
	@Serial
	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundException() {
		super("Data not found");
	}
	
	public ObjectNotFoundException(Class<?> clazz) {
		super(clazz.getCanonicalName() + ": " + "not found");
	}
	
	public ObjectNotFoundException(String msg) {
		super(msg);
	}
	
	public ObjectNotFoundException(Class<?> clazz, String msg) {
		super(clazz.getCanonicalName() + ": " + msg);
	}
	
	public ObjectNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}



