package tn.cita.app.exception.wrapper;

import lombok.Getter;

import java.io.Serial;

@Getter
public class BusinessException extends RuntimeException {
	
	@Serial
	private static final long serialVersionUID = -2376018748031261902L;
	private final String message;
	
	public BusinessException() {
		super("Customized business exception thrown");
		this.message = super.getMessage();
	}
	
	public BusinessException(final String message) {
		super(message);
		this.message = message;
	}
	
	public BusinessException(final String message, final Throwable cause) {
		super(message, cause);
		this.message = message;
	}
	
}



