package tn.cita.app.service;

import java.util.List;

import tn.cita.app.dto.CustomerDto;
import tn.cita.app.dto.response.CustomerContainerResponse;
import tn.cita.app.dto.response.ReservationContainerResponse;

public interface CustomerService {
	
	List<CustomerDto> findAll(final int pageOffset);
	CustomerDto findById(final Integer id);
	CustomerDto findByCredentialUsernameIgnoringCase(final String username);
	boolean deleteById(final Integer id);
	CustomerContainerResponse getProfileByUsername(final String username);
	CustomerContainerResponse getFavouritesByUsername(final String username);
	CustomerContainerResponse getReservationsByUsername(final String username);
	CustomerContainerResponse getRatingsByUsername(final String username);
	ReservationContainerResponse getReservationDetails(final Integer reservationId);
	
}













