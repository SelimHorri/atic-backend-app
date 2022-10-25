package tn.cita.app.service.v0.business.customer.impl;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import tn.cita.app.mapper.CustomerMapper;
import tn.cita.app.mapper.ReservationMapper;
import tn.cita.app.repository.CustomerRepository;
import tn.cita.app.repository.OrderedDetailRepository;
import tn.cita.app.repository.ReservationRepository;
import tn.cita.app.repository.SaloonRepository;
import tn.cita.app.repository.ServiceDetailRepository;
import tn.cita.app.service.v0.business.customer.CustomerReservationService;
import tn.cita.app.service.v0.common.ReservationCommonService;
import tn.cita.app.util.ClientRequestUtils;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class CustomerReservationServiceImpl implements CustomerReservationService {
	
	private final CustomerRepository customerRepository;
	private final ReservationRepository reservationRepository;
	private final ReservationCommonService reservationCommonService;
	private final SaloonRepository saloonRepository;
	private final ServiceDetailRepository serviceDetailRepository;
	private final OrderedDetailRepository orderedDetailRepository;
	
	@Override
	public CustomerReservationResponse fetchAllReservations(final String username, final ClientPageRequest clientPageRequest) {
		log.info("** Fetch all reservations by customer.. *\n");
		final var customerDto = this.customerRepository
				.findByCredentialUsernameIgnoringCase(username)
				.map(CustomerMapper::map)
				.orElseThrow(() -> new CustomerNotFoundException("Customer with username: %s not found".formatted(username)));
		return new CustomerReservationResponse(
				customerDto,
				this.reservationRepository.findAllByCustomerId(customerDto.getId(), 
						ClientRequestUtils.from(clientPageRequest))
					.map(ReservationMapper::map));
	}
	
	@Override
	public CustomerReservationResponse searchAllByCustomerIdLikeKey(final String username, final String key) {
		log.info("** Search all reservations by customerId like key by customer.. *\n");
		final var customerDto = this.customerRepository
				.findByCredentialUsernameIgnoringCase(username)
				.map(CustomerMapper::map)
				.orElseThrow(() -> new CustomerNotFoundException("Customer with username: %s not found".formatted(username)));
		return new CustomerReservationResponse(
				customerDto, 
				new PageImpl<>(this.reservationRepository
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
		
		log.info("** Create new reservation by customer.. *\n");
		
		if (reservationRequest.getStartDate().isBefore(LocalDateTime.now().plusMinutes(AppConstants.VALID_START_DATE_AFTER))
				|| reservationRequest.getStartDate().getMinute() != 0 && reservationRequest.getStartDate().getMinute() != 30)
			throw new OutdatedStartDateReservationException("Illegal Starting date reservation, plz choose a valid date");
		
		this.reservationRepository
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
				.customer(this.customerRepository
						.findByCredentialUsernameIgnoringCase(reservationRequest.getUsername())
						.orElseThrow(() -> new CustomerNotFoundException(String
								.format("Customer with username %s not found", reservationRequest.getUsername()))))
				.saloon(this.saloonRepository
						.findById(reservationRequest.getSaloonId())
						.orElseThrow(() -> new SaloonNotFoundException("Saloon not found")))
				.description((reservationRequest.getDescription() == null 
							|| reservationRequest.getDescription().isBlank()) ? 
						null : reservationRequest.getDescription().strip())
				.build();
		
		final var savedReservation = this.reservationRepository.save(reservation);
		
		final var orderedDetail = new OrderedDetail();
		serviceDetailsIds.forEach(serviceDetailId -> {
			orderedDetail.setReservationId(savedReservation.getId());
			orderedDetail.setServiceDetailId(serviceDetailId);
			orderedDetail.setOrderedDate(LocalDateTime.now());
			orderedDetail.setReservation(savedReservation);
			orderedDetail.setServiceDetail(this.serviceDetailRepository.findById(serviceDetailId)
					.orElseThrow(() -> new ServiceDetailNotFoundException("ServiceDetail not found")));
			// persist...
			this.orderedDetailRepository.saveOrderedDetail(new OrderedDetailRequest(
					orderedDetail.getReservationId(), 
					orderedDetail.getServiceDetailId(), 
					orderedDetail.getOrderedDate()));
		});
		
		return ReservationMapper.map(savedReservation);
	}
	
	
	
}
















