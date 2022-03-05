package tn.cita.app.service;

import tn.cita.app.dto.request.RegisterRequest;
import tn.cita.app.dto.response.RegisterResponse;

public interface RegistrationService {
	
	RegisterResponse registerCustomer(final RegisterRequest registerRequest);
	RegisterResponse registerEmployee(final RegisterRequest registerRequest);
	
}












