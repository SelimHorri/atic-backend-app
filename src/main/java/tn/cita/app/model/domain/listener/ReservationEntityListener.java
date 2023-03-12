package tn.cita.app.model.domain.listener;

import java.util.UUID;

import javax.persistence.PrePersist;

import tn.cita.app.model.domain.ReservationStatus;
import tn.cita.app.model.domain.entity.Reservation;

public class ReservationEntityListener {
	
	@PrePersist
	public void preCreate(final Reservation reservation) {
		reservation.setCode((reservation.getCode() == null || reservation.getCode().isBlank()) ? 
				UUID.randomUUID().toString() : reservation.getCode());
		reservation.setStatus(ReservationStatus.NOT_STARTED);
	}
	
}





