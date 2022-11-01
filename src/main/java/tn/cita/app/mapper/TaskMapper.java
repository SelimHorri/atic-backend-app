package tn.cita.app.mapper;

import java.util.Optional;

import tn.cita.app.model.domain.entity.Employee;
import tn.cita.app.model.domain.entity.Reservation;
import tn.cita.app.model.domain.entity.Task;
import tn.cita.app.model.dto.EmployeeDto;
import tn.cita.app.model.dto.ReservationDto;
import tn.cita.app.model.dto.TaskDto;

public interface TaskMapper {
	
public static TaskDto map(final Task task) {
		
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
	
	public static Task map(final TaskDto taskDto) {
		
		final var workerDto = Optional.ofNullable(taskDto.getWorkerDto())
				.orElseGet(EmployeeDto::new);
		final var reservationDto = Optional.ofNullable(taskDto.getReservationDto())
				.orElseGet(ReservationDto::new);
		
		return Task.builder()
				.workerId(taskDto.getWorkerId())
				.reservationId(taskDto.getReservationId())
				.taskDate(taskDto.getTaskDate())
				.identifier(taskDto.getIdentifier())
				.startDate(taskDto.getStartDate())
				.endDate(taskDto.getEndDate())
				.workerDescription(taskDto.getWorkerDescription())
				.managerDescription(taskDto.getManagerDescription())
				.worker(
					Employee.builder()
						.id(workerDto.getId())
						.identifier(workerDto.getIdentifier())
						.ssn(workerDto.getSsn())
						.firstname(workerDto.getFirstname())
						.lastname(workerDto.getLastname())
						.isMale(workerDto.getIsMale())
						.email(workerDto.getEmail())
						.phone(workerDto.getPhone())
						.birthdate(workerDto.getBirthdate())
						.build())
				.reservation(
					Reservation.builder()
						.id(reservationDto.getId())
						.identifier(reservationDto.getIdentifier())
						.code(reservationDto.getCode())
						.description(reservationDto.getDescription())
						.startDate(reservationDto.getStartDate())
						.cancelDate(reservationDto.getCancelDate())
						.completeDate(reservationDto.getCompleteDate())
						.status(reservationDto.getStatus())
						.build())
				.build();
	}
	
	
}













