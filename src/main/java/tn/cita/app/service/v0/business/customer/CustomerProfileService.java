package tn.cita.app.service.v0.business.customer;

import tn.cita.app.model.dto.CustomerDto;
import tn.cita.app.model.dto.request.ClientPageRequest;
import tn.cita.app.model.dto.request.CustomerProfileRequest;
import tn.cita.app.model.dto.response.CustomerProfileResponse;

public interface CustomerProfileService {
	
	CustomerProfileResponse fetchProfile(final String username, final ClientPageRequest clientPageRequest);
	CustomerDto updateProfileInfo(final CustomerProfileRequest customerProfileRequest);
	
}








