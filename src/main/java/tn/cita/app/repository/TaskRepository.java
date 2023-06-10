package tn.cita.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.cita.app.model.domain.entity.Task;
import tn.cita.app.model.domain.id.TaskId;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, TaskId> {
	
	Optional<Task> findByIdentifier(final String identifier);
	List<Task> findAllByReservationId(final Integer reservationId);
	Page<Task> findAllByReservationId(final Integer reservationId, final Pageable pageable);
	List<Task> findAllByWorkerId(final Integer workerId);
	Page<Task> findAllByWorkerId(final Integer workerId, final Pageable pageable);
	
	@Query(name = "int.saveTask", nativeQuery = true)
	@Modifying
	int saveTask(@Param("task") final Task task);
	
	@Query(name = "List<Task>.searchAllByWorkerIdLikeKey")
	List<Task> searchAllByWorkerIdLikeKey(@Param("workerId") final Integer workerId, @Param("key") final String key);
	
}



