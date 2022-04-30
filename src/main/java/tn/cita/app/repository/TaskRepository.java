package tn.cita.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.cita.app.domain.entity.Task;
import tn.cita.app.domain.id.TaskId;

public interface TaskRepository extends JpaRepository<Task, TaskId> {
	
	
	
}









