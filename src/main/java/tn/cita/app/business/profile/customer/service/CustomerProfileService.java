package tn.cita.app.business.profile.customer.service;

import tn.cita.app.business.profile.customer.model.CustomerProfileRequest;
import tn.cita.app.business.profile.customer.model.CustomerProfileResponse;
import tn.cita.app.model.dto.CustomerDto;
import tn.cita.app.model.dto.request.ClientPageRequest;

public interface CustomerProfileService {
	
	CustomerProfileResponse fetchProfile(final String username, final ClientPageRequest clientPageRequest);
	CustomerDto updateProfileInfo(final CustomerProfileRequest customerProfileRequest);
	
}



