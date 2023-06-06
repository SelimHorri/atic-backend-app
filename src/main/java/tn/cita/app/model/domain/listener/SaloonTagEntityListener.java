package tn.cita.app.model.domain.listener;

import java.time.LocalDateTime;

import jakarta.persistence.PrePersist;

import tn.cita.app.model.domain.entity.SaloonTag;

public class SaloonTagEntityListener {
	
	@PrePersist
	public void preCreate(final SaloonTag saloonTag) {
		saloonTag.setTaggedDate(LocalDateTime.now());
	}
	
}



