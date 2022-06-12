package tn.cita.app.service.v0.business.customer;

import tn.cita.app.dto.CustomerDto;
import tn.cita.app.dto.request.ClientPageRequest;
import tn.cita.app.dto.request.CustomerProfileInfoRequest;
import tn.cita.app.dto.response.CustomerProfileResponse;

public interface CustomerProfileService {
	
	CustomerProfileResponse getProfileByUsername(final String username, final ClientPageRequest clientPageRequest);
	CustomerDto updateProfileInfo(final CustomerProfileInfoRequest customerProfileInfoRequest);
	
}








