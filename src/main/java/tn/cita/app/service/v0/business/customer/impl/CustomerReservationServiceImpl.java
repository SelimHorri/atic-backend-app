package tn.cita.app.service.v0.business.customer.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tn.cita.app.dto.ReservationDto;
import tn.cita.app.dto.request.ClientPageRequest;
import tn.cita.app.dto.response.CustomerReservationResponse;
import tn.cita.app.exception.wrapper.ReservationNotFoundException;
import tn.cita.app.mapper.ReservationMapper;
import tn.cita.app.service.v0.CustomerService;
import tn.cita.app.service.v0.ReservationService;
import tn.cita.app.service.v0.business.customer.CustomerReservationService;

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
	
	@Transactional
	@Override
	public ReservationDto cancelReservation(final ReservationDto reservationDtoRequest) {
		
		final var reservation = this.reservationService.getReservationRepository().findById(reservationDtoRequest.getId())
				.orElseThrow(() -> new ReservationNotFoundException(String
						.format("Reservation with id: %s not found", reservationDtoRequest.getId())));
		
		// update
		reservation.setCancelDate(reservationDtoRequest.getCancelDate());
		reservation.setStatus(reservationDtoRequest.getStatus());
		
		return ReservationMapper.map(this.reservationService.getReservationRepository().save(reservation));
	}
	
	
	
}
















