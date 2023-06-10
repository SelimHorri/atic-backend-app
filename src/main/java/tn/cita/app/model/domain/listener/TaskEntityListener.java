package tn.cita.app.model.domain.listener;

import jakarta.persistence.PrePersist;
import tn.cita.app.model.domain.entity.Task;

import java.time.LocalDateTime;

public class TaskEntityListener {
	
	@PrePersist
	public void preCreate(final Task task) {
		task.setTaskDate(LocalDateTime.now());
	}
	
}



