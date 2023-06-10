package tn.cita.app.model.domain.listener;

import jakarta.persistence.PrePersist;
import tn.cita.app.model.domain.entity.SaloonTag;

import java.time.LocalDateTime;

public class SaloonTagEntityListener {
	
	@PrePersist
	public void preCreate(final SaloonTag saloonTag) {
		saloonTag.setTaggedDate(LocalDateTime.now());
	}
	
}



