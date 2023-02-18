package tn.cita.app.mapper;

import java.util.Optional;

import lombok.NonNull;
import tn.cita.app.model.domain.entity.Employee;
import tn.cita.app.model.domain.entity.Reservation;
import tn.cita.app.model.domain.entity.Task;
import tn.cita.app.model.dto.EmployeeDto;
import tn.cita.app.model.dto.ReservationDto;
import tn.cita.app.model.dto.TaskDto;

public interface TaskMapper {
	
public static TaskDto map(@NonNull final Task task) {
		
		final var worker = Optional.ofNullable(task.getWorker())
				.orElseGet(Employee::new);
		final var reservation = Optional.ofNullable(task.getReservation())
				.orElseGet(Reservation::new);
		
		return TaskDto.builder()
				.workerId(task.getWorkerId())
				.reservationId(task.getReservationId())
				.taskDate(task.getTaskDate())
				.identifier(task.getIdentifier())
				.startDate(task.getStartDate())
				.endDate(task.getEndDate())
				.workerDescription(task.getWorkerDescription())
				.managerDescription(task.getManagerDescription())
				.workerDto(
					EmployeeDto.builder()
						.id(worker.getId())
						.identifier(worker.getIdentifier())
						.ssn(worker.getSsn())
						.firstname(worker.getFirstname())
						.lastname(worker.getLastname())
						.isMale(worker.getIsMale())
						.email(worker.getEmail())
						.phone(worker.getPhone())
						.birthdate(worker.getBirthdate())
						.build())
				.reservationDto(
					ReservationDto.builder()
						.id(reservation.getId())
						.identifier(reservation.getIdentifier())
						.code(reservation.getCode())
						.description(reservation.getDescription())
						.startDate(reservation.getStartDate())
						.cancelDate(reservation.getCancelDate())
						.completeDate(reservation.getCompleteDate())
						.status(reservation.getStatus())
						.build())
				.build();
	}
	
}







