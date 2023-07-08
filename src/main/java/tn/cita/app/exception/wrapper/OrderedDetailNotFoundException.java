package tn.cita.app.exception.wrapper;

import java.io.Serial;

public class OrderedDetailNotFoundException extends ObjectNotFoundException {
	
	@Serial
	private static final long serialVersionUID = -6877038970731972594L;
	
	public OrderedDetailNotFoundException() {
		super(OrderedDetailNotFoundException.class);
	}
	
	public OrderedDetailNotFoundException(String message) {
		super(message);
	}
	
}



