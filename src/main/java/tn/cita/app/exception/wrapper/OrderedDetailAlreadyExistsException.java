package tn.cita.app.exception.wrapper;

import java.io.Serial;

public class OrderedDetailAlreadyExistsException extends BusinessException {
	
	@Serial
	private static final long serialVersionUID = 2672959932252642625L;
	
	public OrderedDetailAlreadyExistsException() {
		super("OrderedDetail already exists");
	}
	
	public OrderedDetailAlreadyExistsException(String message) {
		super(message);
	}
	
}



