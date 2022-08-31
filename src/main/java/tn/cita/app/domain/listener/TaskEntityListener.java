package tn.cita.app.domain.listener;

import java.time.LocalDateTime;

import javax.persistence.PrePersist;

import org.springframework.stereotype.Component;

import tn.cita.app.domain.entity.Task;

@Component
public class TaskEntityListener {
	
	@PrePersist
	public void preCreate(final Task task) {
		task.setTaskDate(LocalDateTime.now());
	}
	
	
	
}










