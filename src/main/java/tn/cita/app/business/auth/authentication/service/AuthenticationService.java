package tn.cita.app.business.auth.authentication.service;

import tn.cita.app.business.auth.authentication.model.LoginRequest;
import tn.cita.app.business.auth.authentication.model.LoginResponse;

public interface AuthenticationService {
	
	LoginResponse authenticate(final LoginRequest loginRequest);
	
}





