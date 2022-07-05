package tn.cita.app.service.v0.business.customer;

import tn.cita.app.dto.CustomerDto;
import tn.cita.app.dto.request.ClientPageRequest;
import tn.cita.app.dto.request.CustomerProfileRequest;
import tn.cita.app.dto.response.CustomerProfileResponse;

public interface CustomerProfileService {
	
	CustomerProfileResponse getProfileByUsername(final String username, final ClientPageRequest clientPageRequest);
	CustomerDto updateProfileInfo(final CustomerProfileRequest customerProfileRequest);
	
}








