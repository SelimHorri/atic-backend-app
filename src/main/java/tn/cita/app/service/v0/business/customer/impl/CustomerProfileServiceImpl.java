package tn.cita.app.service.v0.business.customer.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tn.cita.app.dto.request.ClientPageRequest;
import tn.cita.app.dto.response.CustomerProfileResponse;
import tn.cita.app.service.v0.CustomerService;
import tn.cita.app.service.v0.FavouriteService;
import tn.cita.app.service.v0.RatingService;
import tn.cita.app.service.v0.ReservationService;
import tn.cita.app.service.v0.business.customer.CustomerProfileService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomerProfileServiceImpl implements CustomerProfileService {
	
	private final CustomerService customerService;
	private final ReservationService reservationService;
	private final FavouriteService favouriteService;
	private final RatingService ratingService;
	
	@Override
	public CustomerProfileResponse getProfileByUsername(final String username, final ClientPageRequest clientPageRequest) {
		final var customerDto = this.customerService.findByCredentialUsernameIgnoringCase(username);
		return new CustomerProfileResponse(
				customerDto, 
				null, 
				this.reservationService.findAllByCustomerId(customerDto.getId(), clientPageRequest), 
				this.favouriteService.findAllByCustomerId(customerDto.getId(), clientPageRequest),
				this.ratingService.findAllByCustomerId(customerDto.getId()));
	}
	
	
	
}
















