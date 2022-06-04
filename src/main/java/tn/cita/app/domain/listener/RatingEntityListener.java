package tn.cita.app.domain.listener;

import java.time.LocalDateTime;

import javax.persistence.PrePersist;

import tn.cita.app.domain.entity.Rating;

public class RatingEntityListener {
	
	@PrePersist
	public void preCreate(final Rating rating) {
		rating.setRateDate(LocalDateTime.now());
	}
	
	
	
}









