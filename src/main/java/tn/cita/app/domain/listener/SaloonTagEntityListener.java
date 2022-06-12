package tn.cita.app.domain.listener;

import java.time.LocalDateTime;

import javax.persistence.PrePersist;

import tn.cita.app.domain.entity.SaloonTag;

public class SaloonTagEntityListener {
	
	@PrePersist
	public void preCreate(final SaloonTag saloonTag) {
		saloonTag.setTaggedDate(LocalDateTime.now());
	}
	
	
	
}












