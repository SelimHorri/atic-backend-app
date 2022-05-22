package tn.cita.app.domain.listener;

import java.time.LocalDateTime;

import javax.persistence.PrePersist;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import tn.cita.app.domain.entity.OrderedDetail;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderedDetailEntityListener {
	
	@PrePersist
	void preCreate(final OrderedDetail orderedDetail) {
		orderedDetail.setOrderedDate(LocalDateTime.now());
	}
	
	
	
}













