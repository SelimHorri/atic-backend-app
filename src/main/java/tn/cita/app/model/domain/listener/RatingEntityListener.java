package tn.cita.app.model.domain.listener;

import jakarta.persistence.PrePersist;
import tn.cita.app.model.domain.entity.Rating;

import java.time.LocalDateTime;

public class RatingEntityListener {
	
	@PrePersist
	public void preCreate(final Rating rating) {
		rating.setRateDate(LocalDateTime.now());
	}
	
}



