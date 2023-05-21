package tn.cita.app.model.domain.listener;

import java.time.LocalDateTime;

import jakarta.persistence.PrePersist;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import tn.cita.app.model.domain.entity.OrderedDetail;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderedDetailEntityListener {
	
	@PrePersist
	public void preCreate(final OrderedDetail orderedDetail) {
		orderedDetail.setOrderedDate(LocalDateTime.now());
	}
	
}





