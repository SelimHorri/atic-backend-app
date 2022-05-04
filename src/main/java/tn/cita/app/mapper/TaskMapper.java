package tn.cita.app.mapper;

import tn.cita.app.domain.entity.Employee;
import tn.cita.app.domain.entity.Reservation;
import tn.cita.app.domain.entity.Task;
import tn.cita.app.dto.TaskDto;

public interface TaskMapper {
	
	public static TaskDto map(final Task task) {
		return TaskDto.builder()
				.workerId(task.getWorkerId())
				.reservationId(task.getReservationId())
				.taskDate(task.getTaskDate())
				.startDate(task.getStartDate())
				.endDate(task.getEndDate())
				.workerDescription(task.getWorkerDescription())
				.managerDescription(task.getManagerDescription())
				.workerId(task.getWorkerId())
				.reservationId(task.getReservationId())
				.build();
	}
	
	public static Task map(final TaskDto taskDto) {
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
						.id(taskDto.getWorkerId())
						.build())
				.reservation(
					Reservation.builder()
						.id(taskDto.getReservationId())
						.build())
				.build();
	}
	
	
	
}













