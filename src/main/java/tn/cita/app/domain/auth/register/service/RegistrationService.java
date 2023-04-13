package tn.cita.app.domain.auth.register.service;

import tn.cita.app.domain.auth.register.model.RegisterRequest;
import tn.cita.app.domain.auth.register.model.RegisterResponse;

public interface RegistrationService {
	
	RegisterResponse register(final RegisterRequest registerRequest);
	String validateToken(final String token);
	
}





