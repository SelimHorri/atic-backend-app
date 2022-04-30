package tn.cita.app.mapper;

import java.util.Optional;

import tn.cita.app.domain.entity.Employee;
import tn.cita.app.domain.entity.Reservation;
import tn.cita.app.domain.entity.Task;
import tn.cita.app.dto.EmployeeDto;
import tn.cita.app.dto.ReservationDto;
import tn.cita.app.dto.TaskDto;

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
				.startDate(task.getStartDate())
				.endDate(task.getEndDate())
				.workerDescription(task.getWorkerDescription())
				.managerDescription(task.getManagerDescription())
				.workerDto(
					EmployeeDto.builder()
						.id(worker.getId())
						.firstname(worker.getFirstname())
						.lastname(worker.getLastname())
						.email(worker.getEmail())
						.phone(worker.getPhone())
						.birthdate(worker.getBirthdate())
						.build())
				.reservationDto(
					ReservationDto.builder()
						.id(reservation.getId())
						.code(reservation.getCode())
						.description(reservation.getDescription())
						.startDate(reservation.getStartDate())
						.cancelDate(reservation.getCancelDate())
						.reservationStatus(reservation.getReservationStatus())
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
				.startDate(taskDto.getStartDate())
				.endDate(taskDto.getEndDate())
				.workerDescription(taskDto.getWorkerDescription())
				.managerDescription(taskDto.getManagerDescription())
				.worker(
					Employee.builder()
						.id(workerDto.getId())
						.firstname(workerDto.getFirstname())
						.lastname(workerDto.getLastname())
						.email(workerDto.getEmail())
						.phone(workerDto.getPhone())
						.birthdate(workerDto.getBirthdate())
						.build())
				.reservation(
					Reservation.builder()
						.id(reservationDto.getId())
						.code(reservationDto.getCode())
						.description(reservationDto.getDescription())
						.startDate(reservationDto.getStartDate())
						.cancelDate(reservationDto.getCancelDate())
						.reservationStatus(reservationDto.getReservationStatus())
						.build())
				.build();
	}
	
	
	
}













