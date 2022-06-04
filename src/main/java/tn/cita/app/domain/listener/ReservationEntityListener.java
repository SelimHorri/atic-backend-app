package tn.cita.app.domain.listener;

import java.util.UUID;

import javax.persistence.PrePersist;

import tn.cita.app.domain.ReservationStatus;
import tn.cita.app.domain.entity.Reservation;

public class ReservationEntityListener {
	
	@PrePersist
	public void preCreate(final Reservation reservation) {
		reservation.setCode(UUID.randomUUID().toString());
		reservation.setStatus(ReservationStatus.NOT_STARTED);
	}
	
	
	
}













