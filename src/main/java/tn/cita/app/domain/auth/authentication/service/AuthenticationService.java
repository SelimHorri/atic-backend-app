package tn.cita.app.domain.auth.authentication.service;

import tn.cita.app.domain.auth.authentication.model.LoginRequest;
import tn.cita.app.domain.auth.authentication.model.LoginResponse;

public interface AuthenticationService {
	
	LoginResponse authenticate(final LoginRequest loginRequest);
	
}





