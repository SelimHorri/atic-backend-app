package tn.cita.app.service.v0.business.employee.worker.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tn.cita.app.dto.response.ReservationDetailResponse;
import tn.cita.app.service.v0.OrderedDetailService;
import tn.cita.app.service.v0.ReservationService;
import tn.cita.app.service.v0.TaskService;
import tn.cita.app.service.v0.business.employee.worker.WorkerReservationDetailService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WorkerReservationDetailServiceImpl implements WorkerReservationDetailService {
	
	// private final EmployeeService employeeService;
	private final TaskService taskService;
	private final ReservationService reservationService;
	private final OrderedDetailService orderedDetailService;
	
	@Override
	public ReservationDetailResponse getReservationDetails(final Integer reservationId) {
		final var reservationDto = this.reservationService.findById(reservationId);
		return ReservationDetailResponse.builder()
				.reservationDto(reservationDto)
				.orderedDetailDtos(this.orderedDetailService.findAllByReservationId(reservationDto.getId()))
				.taskDtos(this.taskService.findAllByReservationId(reservationDto.getId()))
				.build();
	}
	
	
	
}














