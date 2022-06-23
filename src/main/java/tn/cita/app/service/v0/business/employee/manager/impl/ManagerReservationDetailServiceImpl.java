package tn.cita.app.service.v0.business.employee.manager.impl;

import java.util.Comparator;
import java.util.Optional;

import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tn.cita.app.domain.ReservationStatus;
import tn.cita.app.dto.TaskDto;
import tn.cita.app.dto.response.ReservationBeginEndTask;
import tn.cita.app.dto.response.ReservationDetailResponse;
import tn.cita.app.service.v0.OrderedDetailService;
import tn.cita.app.service.v0.ReservationService;
import tn.cita.app.service.v0.TaskService;
import tn.cita.app.service.v0.business.employee.manager.ManagerReservationDetailService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ManagerReservationDetailServiceImpl implements ManagerReservationDetailService {
	
	private final ReservationService reservationService;
	private final OrderedDetailService orderedDetailService;
	private final TaskService taskService;
	
	@Override
	public ReservationDetailResponse getReservationDetails(final Integer reservationId) {
		final var reservationDto = this.reservationService.findById(reservationId);
		return ReservationDetailResponse.builder()
				.reservationDto(reservationDto)
				.orderedDetailDtos(new PageImpl<>(this.orderedDetailService.findAllByReservationId(reservationDto.getId())))
				.build();
	}
	
	@Override
	public ReservationBeginEndTask getBeginEndTask(final Integer reservationId) {
		
		final var taskDtos = this.taskService.findAllByReservationId(reservationId);
		
		final var firstTaskBegin = taskDtos.stream()
				.filter(t -> Optional.ofNullable(t.getStartDate()).isPresent())
				// .filter(t -> t.getReservationDto().getStatus().equals(ReservationStatus.IN_PROGRESS))
				.min(Comparator.comparing(TaskDto::getStartDate))
				.orElseGet(TaskDto::new);
		final var lastTaskEnd = taskDtos.stream()
				.filter(t -> Optional.ofNullable(t.getEndDate()).isPresent())
				.filter(t -> t.getReservationDto().getStatus().equals(ReservationStatus.COMPLETED))
				.max(Comparator.comparing(TaskDto::getEndDate))
				.orElseGet(TaskDto::new);
		
		return new ReservationBeginEndTask(firstTaskBegin, lastTaskEnd);
	}
	
	
	
}
















