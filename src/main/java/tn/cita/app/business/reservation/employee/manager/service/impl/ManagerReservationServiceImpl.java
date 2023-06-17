package tn.cita.app.business.reservation.employee.manager.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.cita.app.business.reservation.ReservationCommonService;
import tn.cita.app.business.reservation.employee.manager.model.ManagerReservationResponse;
import tn.cita.app.business.reservation.employee.manager.model.ReservationAssignWorkerRequest;
import tn.cita.app.business.reservation.employee.manager.model.ReservationSubWorkerResponse;
import tn.cita.app.business.reservation.employee.manager.service.ManagerReservationService;
import tn.cita.app.exception.wrapper.EmployeeNotFoundException;
import tn.cita.app.mapper.EmployeeMapper;
import tn.cita.app.mapper.ReservationMapper;
import tn.cita.app.model.dto.EmployeeDto;
import tn.cita.app.model.dto.ReservationDto;
import tn.cita.app.model.dto.request.ClientPageRequest;
import tn.cita.app.repository.EmployeeRepository;
import tn.cita.app.repository.ReservationRepository;
import tn.cita.app.util.ClientPageRequestUtils;

import java.util.Comparator;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class ManagerReservationServiceImpl implements ManagerReservationService {
	
	private final EmployeeRepository employeeRepository;
	private final ReservationRepository reservationRepository;
	private final ReservationCommonService reservationCommonService;
	
	@Override
	public ManagerReservationResponse fetchAllReservations(final String username, final ClientPageRequest clientPageRequest) {
		log.info("** Fetch all reservations by manager.. *");
		final var managerDto = this.retrieveManagerByUsername(username);
		return new ManagerReservationResponse(managerDto,
				this.retrieveReservationsBySaloonId(managerDto.getSaloonDto().getId(), clientPageRequest));
	}
	
	private Page<ReservationDto> retrieveReservationsBySaloonId(final int saloonId, final ClientPageRequest clientPageRequest) {
		return (clientPageRequest != null) ?
				this.reservationRepository
						.findAllBySaloonId(saloonId, ClientPageRequestUtils.from(clientPageRequest))
						.map(ReservationMapper::toDto)
				:
				new PageImpl<>(this.reservationRepository.findAllBySaloonId(saloonId).stream()
						.map(ReservationMapper::toDto)
						.sorted(Comparator
								.comparing(ReservationDto::getStartDate)
								.reversed())
						.toList());
	}
	
	@Transactional
	@Override
	public ReservationDto cancelReservation(final Integer reservationId) {
		return this.reservationCommonService.cancelReservation(reservationId);
	}
	
	@Override
	public ManagerReservationResponse searchAllBySaloonIdLikeKey(final String username, final String key) {
		log.info("** Search all reservations by saloonId like key by manager.. *");
		final var managerDto = this.retrieveManagerByUsername(username);
		final var foundReservations = this.reservationRepository
				.searchAllBySaloonIdLikeKey(managerDto.getSaloonDto().getId(), key.strip().toLowerCase()).stream()
					.map(ReservationMapper::toDto)
					.sorted(Comparator
							.comparing(ReservationDto::getStartDate)
							.reversed())
					.toList();
		return new ManagerReservationResponse(managerDto, new PageImpl<>(foundReservations));
	}
	
	@Override
	public ReservationSubWorkerResponse fetchAllUnassignedSubWorkers(final String username, final Integer reservationId) {
		return this.reservationCommonService.fetchAllUnassignedSubWorkers(username, reservationId);
	}
	
	@Transactional
	@Override
	public ReservationSubWorkerResponse assignReservationWorkers(final String username, 
			final ReservationAssignWorkerRequest reservationAssignWorkerRequest) {
		return this.reservationCommonService.assignReservationWorkers(username, reservationAssignWorkerRequest);
	}
	
	private EmployeeDto retrieveManagerByUsername(final String username) {
		return this.employeeRepository
				.findByCredentialUsernameIgnoringCase(username)
				.map(EmployeeMapper::toDto)
				.orElseThrow(() -> new EmployeeNotFoundException(
						"Employee with username: %s not found".formatted(username)));
	}
	
}



