package tn.cita.app.service.v0.business.employee.manager.impl;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tn.cita.app.dto.ReservationDto;
import tn.cita.app.dto.request.ClientPageRequest;
import tn.cita.app.dto.request.ReservationAssignWorkerRequest;
import tn.cita.app.dto.response.ManagerReservationResponse;
import tn.cita.app.dto.response.ReservationSubWorkerResponse;
import tn.cita.app.mapper.ReservationMapper;
import tn.cita.app.service.v0.EmployeeService;
import tn.cita.app.service.v0.ReservationService;
import tn.cita.app.service.v0.business.employee.manager.ManagerReservationService;
import tn.cita.app.service.v0.common.ReservationCommonService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ManagerReservationServiceImpl implements ManagerReservationService {
	
	private final EmployeeService employeeService;
	private final ReservationService reservationService;
	private final ReservationCommonService reservationCommonService;
	
	@Override
	public ManagerReservationResponse fetchAllReservations(final String username, final ClientPageRequest clientPageRequest) {
		final var managerDto = this.employeeService.findByCredentialUsername(username);
		if (Optional.ofNullable(clientPageRequest).isPresent())
			return new ManagerReservationResponse(
					managerDto, 
					this.reservationService.findAllBySaloonId(managerDto.getSaloonDto().getId(), clientPageRequest));
		else
			return new ManagerReservationResponse(
					managerDto, 
					new PageImpl<>(this.reservationService
							.findAllBySaloonId(managerDto.getSaloonDto().getId()).stream()
								.sorted(Comparator.comparing(ReservationDto::getStartDate).reversed())
								.collect(Collectors.toUnmodifiableList())));
	}
	
	@Transactional
	@Override
	public ReservationDto cancelReservation(final Integer reservationId) {
		return this.reservationCommonService.cancelReservation(reservationId);
	}
	
	@Override
	public ManagerReservationResponse searchAllBySaloonIdLikeKey(final String username, final String key) {
		final var managerDto = this.employeeService.findByCredentialUsername(username);
		return new ManagerReservationResponse(
				managerDto, 
				new PageImpl<>(this.reservationService.getReservationRepository()
						.searchAllBySaloonIdLikeKey(managerDto.getSaloonDto().getId(), key.strip().toLowerCase()).stream()
							.map(ReservationMapper::map)
							.distinct()
							.sorted(Comparator.comparing(ReservationDto::getStartDate).reversed())
							.collect(Collectors.toUnmodifiableList())));
	}
	
	@Override
	public ReservationSubWorkerResponse fetchAllUnassignedSubWorkers(final String username, final Integer reservationId) {
		return this.reservationCommonService.getAllUnassignedSubWorkers(username, reservationId);
	}
	
	@Transactional
	@Override
	public ReservationSubWorkerResponse assignReservationWorkers(final String username, 
			final ReservationAssignWorkerRequest reservationAssignWorkerRequest) {
		return this.reservationCommonService.assignReservationWorkers(username, reservationAssignWorkerRequest);
	}
	
	
	
}

















