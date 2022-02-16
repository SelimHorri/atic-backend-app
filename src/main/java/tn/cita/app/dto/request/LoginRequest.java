package tn.cita.app.dto.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
public final class LoginRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "Input username should not be blank")
	private final String username;
	
	@NotBlank(message = "Input password should not be blank")
	private final String password;
	
}












