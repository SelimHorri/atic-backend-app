package tn.cita.app.service.v0.customer.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tn.cita.app.dto.request.ClientPageRequest;
import tn.cita.app.dto.response.CustomerReservationResponse;
import tn.cita.app.service.v0.CustomerService;
import tn.cita.app.service.v0.ReservationService;
import tn.cita.app.service.v0.customer.CustomerReservationService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomerReservationServiceImpl implements CustomerReservationService {
	
	private final CustomerService customerService;
	private final ReservationService reservationService;
	
	@Override
	public CustomerReservationResponse getReservationsByUsername(final String username, final ClientPageRequest clientPageRequest) {
		final var customerDto = this.customerService.findByCredentialUsernameIgnoringCase(username);
		return new CustomerReservationResponse(
				customerDto,
				this.reservationService.findAllByCustomerId(customerDto.getId(), clientPageRequest));
	}
	
	
	
}
















