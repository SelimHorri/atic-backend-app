package tn.cita.app.model.domain.listener;

import jakarta.persistence.PrePersist;
import tn.cita.app.model.domain.ReservationStatus;
import tn.cita.app.model.domain.entity.Reservation;

import java.util.UUID;

public class ReservationEntityListener {
	
	@PrePersist
	public void preCreate(final Reservation reservation) {
		reservation.setCode((reservation.getCode() == null || reservation.getCode().isBlank()) ? 
				UUID.randomUUID().toString() : reservation.getCode());
		reservation.setStatus(ReservationStatus.NOT_STARTED);
	}
	
}



