package tn.cita.app.service.v0;

import tn.cita.app.model.dto.request.LoginRequest;
import tn.cita.app.model.dto.response.LoginResponse;

public interface AuthenticationService {
	
	LoginResponse authenticate(final LoginRequest loginRequest);
	
}





