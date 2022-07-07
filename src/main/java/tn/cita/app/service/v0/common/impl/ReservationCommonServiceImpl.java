package tn.cita.app.service.v0.common.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tn.cita.app.domain.ReservationStatus;
import tn.cita.app.dto.ReservationDto;
import tn.cita.app.exception.wrapper.ReservationAlreadyCompletedException;
import tn.cita.app.exception.wrapper.ReservationNotFoundException;
import tn.cita.app.mapper.ReservationMapper;
import tn.cita.app.service.v0.ReservationService;
import tn.cita.app.service.v0.common.ReservationCommonService;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationCommonServiceImpl implements ReservationCommonService {
	
	private final ReservationService reservationService;
	
	@Override
	public ReservationDto cancelReservation(final Integer reservationId) {

		final var reservation = this.reservationService.getReservationRepository().findById(reservationId)
				.orElseThrow(() -> new ReservationNotFoundException(String
						.format("Reservation with id: %s not found", reservationId)));
		
		// TODO: check if reservation has been completed, should not be cancelled (or changed) anymore
		// Add completeDate of reservation along with COMPLETED status to this check
		if (reservation.getStatus().equals(ReservationStatus.COMPLETED))
			throw new ReservationAlreadyCompletedException("Reservation is already completed");
		else if (reservation.getStatus().equals(ReservationStatus.CANCELLED))
			throw new ReservationAlreadyCompletedException("Reservation is already cancelled");
		else if (reservation.getStatus().equals(ReservationStatus.OUTDATED))
			throw new ReservationAlreadyCompletedException("Reservation is already outdated");
		
		// update
		reservation.setCancelDate(LocalDateTime.now());
		reservation.setStatus(ReservationStatus.CANCELLED);
		
		return ReservationMapper.map(this.reservationService.getReservationRepository().save(reservation));
	}
	
	
	
}













