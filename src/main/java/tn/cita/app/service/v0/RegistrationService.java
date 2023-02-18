package tn.cita.app.service.v0;

import tn.cita.app.model.dto.request.RegisterRequest;
import tn.cita.app.model.dto.response.RegisterResponse;

public interface RegistrationService {
	
	RegisterResponse register(final RegisterRequest registerRequest);
	String validateToken(final String token);
	
}





