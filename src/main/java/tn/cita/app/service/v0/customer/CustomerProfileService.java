package tn.cita.app.service.v0.customer;

import tn.cita.app.dto.request.ClientPageRequest;
import tn.cita.app.dto.response.CustomerProfileResponse;

public interface CustomerProfileService {
	
	CustomerProfileResponse getProfileByUsername(final String username, final ClientPageRequest clientPageRequest);
	
}








