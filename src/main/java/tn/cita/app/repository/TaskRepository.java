package tn.cita.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import tn.cita.app.domain.entity.Task;
import tn.cita.app.domain.id.TaskId;

public interface TaskRepository extends JpaRepository<Task, TaskId> {
	
	List<Task> findAllByReservationId(final Integer reservationId);
	Page<Task> findAllByReservationId(final Integer reservationId, final Pageable pageable);
	List<Task> findAllByWorkerId(final Integer workerId);
	Page<Task> findAllByWorkerId(final Integer workerId, final Pageable pageable);
	
}









