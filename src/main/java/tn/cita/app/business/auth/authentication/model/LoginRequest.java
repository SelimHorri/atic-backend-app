package tn.cita.app.business.auth.authentication.model;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public record LoginRequest(
		
		@NotBlank(message = "Input username should not be blank") 
		String username, 
		@NotBlank(message = "Input password should not be blank") 
		String password) implements Serializable {}


