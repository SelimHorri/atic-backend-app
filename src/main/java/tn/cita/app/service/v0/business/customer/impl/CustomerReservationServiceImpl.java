package tn.cita.app.service.v0.business.customer.impl;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tn.cita.app.constant.AppConstants;
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
import tn.cita.app.exception.wrapper.SaloonNotFoundException;
import tn.cita.app.exception.wrapper.ServiceDetailNotFoundException;
import tn.cita.app.mapper.ReservationMapper;
import tn.cita.app.service.v0.CustomerService;
import tn.cita.app.service.v0.OrderedDetailService;
import tn.cita.app.service.v0.ReservationService;
import tn.cita.app.service.v0.SaloonService;
import tn.cita.app.service.v0.ServiceDetailService;
import tn.cita.app.service.v0.business.customer.CustomerReservationService;
import tn.cita.app.service.v0.common.ReservationCommonService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomerReservationServiceImpl implements CustomerReservationService {
	
	private final CustomerService customerService;
	private final ReservationService reservationService;
	private final ReservationCommonService reservationCommonService;
	private final SaloonService saloonService;
	private final ServiceDetailService serviceDetailService;
	private final OrderedDetailService orderedDetailService;
	
	@Override
	public CustomerReservationResponse getReservationsByUsername(final String username, final ClientPageRequest clientPageRequest) {
		final var customerDto = this.customerService.findByCredentialUsername(username);
		return new CustomerReservationResponse(
				customerDto,
				this.reservationService.findAllByCustomerId(customerDto.getId(), clientPageRequest));
	}
	
	@Override
	public CustomerReservationResponse searchAllByCustomerIdLikeKey(final String username, final String key) {
		final var customerDto = this.customerService.findByCredentialUsername(username);
		return new CustomerReservationResponse(
				customerDto, 
				new PageImpl<>(this.reservationService.getReservationRepository()
						.searchAllByCustomerIdLikeKey(customerDto.getId(), key.strip().toLowerCase()).stream()
							.map(ReservationMapper::map)
							.distinct()
							.sorted(Comparator.comparing(ReservationDto::getStartDate).reversed())
							.collect(Collectors.toUnmodifiableList())));
	}
	
	@Transactional
	@Override
	public ReservationDto cancelReservation(final Integer reservationId) {
		return this.reservationCommonService.cancelReservation(reservationId);
	}
	
	@Transactional
	@Override
	public ReservationDto createReservation(final ReservationRequest reservationRequest) {
		
		if (reservationRequest.getStartDate().isBefore(LocalDateTime.now().plusMinutes(AppConstants.VALID_START_DATE_AFTER))
				|| reservationRequest.getStartDate().getMinute() != 0 && reservationRequest.getStartDate().getMinute() != 30)
			throw new OutdatedStartDateReservationException("Illegal Starting date reservation, plz choose a valid date");
		
		this.reservationService.getReservationRepository()
				.findByStartDateAndStatus(reservationRequest.getStartDate(), ReservationStatus.NOT_STARTED).ifPresent(r -> {
			throw new ReservationAlreadyExistsException("Time requested is occupied! please choose another time");
		});
		
		final var serviceDetailsIds = reservationRequest
				.getServiceDetailsIds().stream()
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
















