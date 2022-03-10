package tn.cita.app.service;

import tn.cita.app.dto.request.RegisterRequest;
import tn.cita.app.dto.response.RegisterResponse;

public interface RegistrationService {
	
	RegisterResponse register(final RegisterRequest registerRequest);
	String validateToken(final String token);
	
}












