package tn.cita.app.service;

import tn.cita.app.dto.request.LoginRequest;
import tn.cita.app.dto.response.LoginResponse;

public interface AuthenticationService {
	
	LoginResponse login(final LoginRequest loginRequest);
	
}










