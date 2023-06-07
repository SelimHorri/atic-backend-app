package tn.cita.app.business.auth.register.service;

import tn.cita.app.business.auth.register.model.RegisterRequest;
import tn.cita.app.business.auth.register.model.RegisterResponse;

public interface RegistrationService {
	
	RegisterResponse register(final RegisterRequest registerRequest);
	String validateToken(final String token);
	RegisterResponse resendToken(final String username);
	
}





