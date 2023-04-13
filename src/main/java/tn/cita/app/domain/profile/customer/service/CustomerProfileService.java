package tn.cita.app.domain.profile.customer.service;

import tn.cita.app.domain.profile.customer.model.CustomerProfileRequest;
import tn.cita.app.domain.profile.customer.model.CustomerProfileResponse;
import tn.cita.app.model.dto.CustomerDto;
import tn.cita.app.model.dto.request.ClientPageRequest;

public interface CustomerProfileService {
	
	CustomerProfileResponse fetchProfile(final String username, final ClientPageRequest clientPageRequest);
	CustomerDto updateProfileInfo(final CustomerProfileRequest customerProfileRequest);
	
}






