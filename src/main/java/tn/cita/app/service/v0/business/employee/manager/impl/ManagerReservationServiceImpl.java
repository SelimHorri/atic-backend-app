package tn.cita.app.service.v0.business.employee.manager.impl;

import java.util.Comparator;

import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.exception.wrapper.EmployeeNotFoundException;
import tn.cita.app.mapper.EmployeeMapper;
import tn.cita.app.mapper.ReservationMapper;
import tn.cita.app.model.dto.ReservationDto;
import tn.cita.app.model.dto.request.ClientPageRequest;
import tn.cita.app.model.dto.request.ReservationAssignWorkerRequest;
import tn.cita.app.model.dto.response.ManagerReservationResponse;
import tn.cita.app.model.dto.response.ReservationSubWorkerResponse;
import tn.cita.app.repository.EmployeeRepository;
import tn.cita.app.repository.ReservationRepository;
import tn.cita.app.service.v0.business.employee.manager.ManagerReservationService;
import tn.cita.app.service.v0.common.ReservationCommonService;
import tn.cita.app.util.ClientPageRequestUtils;

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
		log.info("** Fetch all paged reservations by manager.. *\n");
		final var managerDto = this.employeeRepository
				.findByCredentialUsernameIgnoringCase(username)
				.map(EmployeeMapper::map)
				.orElseThrow(() -> new EmployeeNotFoundException(String
						.format("Employee with username: %s not found", username)));
		if (clientPageRequest != null)
			return new ManagerReservationResponse(
					managerDto, 
					this.reservationRepository
						.findAllBySaloonId(managerDto.getSaloonDto().getId(), ClientPageRequestUtils.from(clientPageRequest))
						.map(ReservationMapper::map));
		else
			return new ManagerReservationResponse(
					managerDto, 
					new PageImpl<>(this.reservationRepository
							.findAllBySaloonId(managerDto.getSaloonDto().getId()).stream()
							.map(ReservationMapper::map)
							.distinct()
							.sorted(Comparator.comparing(ReservationDto::getStartDate).reversed())
							.toList()));
	}
	
	@Transactional
	@Override
	public ReservationDto cancelReservation(final Integer reservationId) {
		return this.reservationCommonService.cancelReservation(reservationId);
	}
	
	@Override
	public ManagerReservationResponse searchAllBySaloonIdLikeKey(final String username, final String key) {
		log.info("** Search all reservations by saloonId like key by manager.. *\n");
		final var managerDto = this.employeeRepository
				.findByCredentialUsernameIgnoringCase(username)
				.map(EmployeeMapper::map)
				.orElseThrow(() -> new EmployeeNotFoundException(String
						.format("Employee with username: %s not found", username)));
		return new ManagerReservationResponse(
				managerDto, 
				new PageImpl<>(this.reservationRepository
						.searchAllBySaloonIdLikeKey(managerDto.getSaloonDto().getId(), key.strip().toLowerCase()).stream()
							.map(ReservationMapper::map)
							.distinct()
							.sorted(Comparator.comparing(ReservationDto::getStartDate).reversed())
							.toList()));
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
	
}









