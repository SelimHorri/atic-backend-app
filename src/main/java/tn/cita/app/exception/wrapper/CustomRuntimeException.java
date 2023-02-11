package tn.cita.app.exception.wrapper;

import lombok.Getter;

@Getter
public class CustomRuntimeException extends RuntimeException {
	
	private static final long serialVersionUID = -2376018748031261902L;
	private final String message;
	
	public CustomRuntimeException() {
		super("Customized exception thrown");
		this.message = super.getMessage();
	}
	
	public CustomRuntimeException(final String message) {
		super(message);
		this.message = message;
	}
	
	public CustomRuntimeException(final String message, final Throwable cause) {
		super(message, cause);
		this.message = message;
	}
	
}






