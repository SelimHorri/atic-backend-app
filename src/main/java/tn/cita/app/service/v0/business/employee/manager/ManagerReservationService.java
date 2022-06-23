package tn.cita.app.service.v0.business.employee.manager;

import tn.cita.app.dto.request.ClientPageRequest;
import tn.cita.app.dto.response.ManagerReservationResponse;

public interface ManagerReservationService {
	
	ManagerReservationResponse getAllReservations(final String username, final ClientPageRequest clientPageRequest);
	ManagerReservationResponse searchAllBy(final String username, final String key);
	
}












