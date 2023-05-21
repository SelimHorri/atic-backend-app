package tn.cita.app.model.domain.listener;

import java.time.LocalDateTime;

import jakarta.persistence.PrePersist;

import tn.cita.app.model.domain.entity.Task;

public class TaskEntityListener {
	
	@PrePersist
	public void preCreate(final Task task) {
		task.setTaskDate(LocalDateTime.now());
	}
	
}





