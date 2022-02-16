package tn.cita.app.dto.response;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
public final class LoginResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private final String username;
	private final String jwtToken;
	
}











