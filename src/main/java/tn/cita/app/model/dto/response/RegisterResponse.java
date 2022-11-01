package tn.cita.app.model.dto.response;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
public final class RegisterResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Boolean isSuccess = true;
	private final String msg;
	
}







