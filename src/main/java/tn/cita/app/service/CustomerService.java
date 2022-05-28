package tn.cita.app.service;

import org.springframework.data.domain.Page;

import tn.cita.app.dto.CustomerDto;
import tn.cita.app.dto.request.ClientPageRequest;
import tn.cita.app.dto.response.CustomerFavouriteResponse;
import tn.cita.app.dto.response.CustomerProfileResponse;
import tn.cita.app.dto.response.CustomerRatingResponse;
import tn.cita.app.dto.response.CustomerReservationResponse;

public interface CustomerService {
	
	Page<CustomerDto> findAll(final int pageOffset);
	CustomerDto findById(final Integer id);
	CustomerDto findByCredentialUsernameIgnoringCase(final String username);
	boolean deleteById(final Integer id);
	CustomerProfileResponse getProfileByUsername(final String username, final ClientPageRequest clientPageRequest);
	CustomerFavouriteResponse getFavouritesByUsername(final String username, final ClientPageRequest clientPageRequest);
	CustomerReservationResponse getReservationsByUsername(final String username, final ClientPageRequest clientPageRequest);
	CustomerRatingResponse getRatingsByUsername(final String username);
	Boolean deleteFavourite(final String username, final Integer saloonId);
	
}













