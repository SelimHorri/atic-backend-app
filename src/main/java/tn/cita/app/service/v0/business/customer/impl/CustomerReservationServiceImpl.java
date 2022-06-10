package tn.cita.app.service.v0.business.customer.impl;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tn.cita.app.constant.AppConstant;
import tn.cita.app.domain.ReservationStatus;
import tn.cita.app.domain.entity.OrderedDetail;
import tn.cita.app.domain.entity.Reservation;
import tn.cita.app.dto.ReservationDto;
import tn.cita.app.dto.request.ClientPageRequest;
import tn.cita.app.dto.request.OrderedDetailRequest;
import tn.cita.app.dto.request.ReservationRequest;
import tn.cita.app.dto.response.CustomerReservationResponse;
import tn.cita.app.exception.wrapper.CustomerNotFoundException;
import tn.cita.app.exception.wrapper.OutdatedStartDateReservationException;
import tn.cita.app.exception.wrapper.ReservationAlreadyExistsException;
import tn.cita.app.exception.wrapper.ReservationNotFoundException;
import tn.cita.app.exception.wrapper.SaloonNotFoundException;
import tn.cita.app.exception.wrapper.ServiceDetailNotFoundException;
import tn.cita.app.mapper.ReservationMapper;
import tn.cita.app.service.v0.CustomerService;
import tn.cita.app.service.v0.OrderedDetailService;
import tn.cita.app.service.v0.ReservationService;
import tn.cita.app.service.v0.SaloonService;
import tn.cita.app.service.v0.ServiceDetailService;
import tn.cita.app.service.v0.business.customer.CustomerReservationService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomerReservationServiceImpl implements CustomerReservationService {
	
	private final CustomerService customerService;
	private final ReservationService reservationService;
	private final SaloonService saloonService;
	private final ServiceDetailService serviceDetailService;
	private final OrderedDetailService orderedDetailService;
	
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
	
	@Transactional
	@Override
	public ReservationDto createReservation(final ReservationRequest reservationRequest) {
		
		if (reservationRequest.getStartDate().isBefore(LocalDateTime.now().plusMinutes(AppConstant.VALID_START_DATE_AFTER))
				|| (reservationRequest.getStartDate().getMinute() != 0 && reservationRequest.getStartDate().getMinute() != 30))
			throw new OutdatedStartDateReservationException("Illegal Starting date reservation, plz choose a valid date");
		
		this.reservationService.getReservationRepository()
				.findByStartDateAndStatus(reservationRequest.getStartDate(), ReservationStatus.NOT_STARTED).ifPresent(r -> {
			throw new ReservationAlreadyExistsException("Time requested is occupied! please choose another time");
		});
		
		final var serviceDetailsIds = reservationRequest.getServiceDetailsIds()
				.stream()
					.distinct()
					.sorted()
					.collect(Collectors.toUnmodifiableList());
		
		final var reservation = Reservation.builder()
				.startDate(reservationRequest.getStartDate())
				.customer(this.customerService.getCustomerRepository()
							.findByCredentialUsernameIgnoringCase(reservationRequest.getUsername())
						.orElseThrow(() -> new CustomerNotFoundException(String
								.format("Customer with username %s not found", reservationRequest.getUsername()))))
				.saloon(this.saloonService.getSaloonRepository().findById(reservationRequest.getSaloonId())
						.orElseThrow(() -> new SaloonNotFoundException(String
								.format("Saloon with id: %d not found", reservationRequest.getSaloonId()))))
				.description(reservationRequest.getDescription())
				.build();
		
		final var savedReservation = this.reservationService.getReservationRepository().save(reservation);
		
		final var orderedDetail = new OrderedDetail();
		serviceDetailsIds.forEach(serviceDetailId -> {
			orderedDetail.setReservationId(savedReservation.getId());
			orderedDetail.setServiceDetailId(serviceDetailId);
			orderedDetail.setOrderedDate(LocalDateTime.now());
			orderedDetail.setReservation(savedReservation);
			orderedDetail.setServiceDetail(this.serviceDetailService.getServiceDetailRepository().findById(serviceDetailId)
					.orElseThrow(() -> new ServiceDetailNotFoundException(String
							.format("ServiceDetail with id: %d not found", serviceDetailId))));
			// persist...
			this.orderedDetailService.getOrderedDetailRepository().saveOrderedDetail(new OrderedDetailRequest(
					orderedDetail.getReservationId(), 
					orderedDetail.getServiceDetailId(), 
					orderedDetail.getOrderedDate()));
		});
		
		return ReservationMapper.map(savedReservation);
	}
	
	
	
}
















