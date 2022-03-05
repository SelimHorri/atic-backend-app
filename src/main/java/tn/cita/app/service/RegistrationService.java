package tn.cita.app.service;

import tn.cita.app.dto.request.RegisterRequest;
import tn.cita.app.dto.response.RegisterResponse;

public interface RegistrationService {
	
	String validateTokenCustmoer(final String token);
	RegisterResponse registerCustomer(final RegisterRequest registerRequest);
	RegisterResponse registerEmployee(final RegisterRequest registerRequest);
	
}












