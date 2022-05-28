package tn.cita.app.service.v0.customer;

import tn.cita.app.dto.request.ClientPageRequest;
import tn.cita.app.dto.response.CustomerReservationResponse;

public interface CustomerReservationService {
	
	CustomerReservationResponse getReservationsByUsername(final String username, final ClientPageRequest clientPageRequest);
	
}








