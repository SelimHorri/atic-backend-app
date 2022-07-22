package tn.cita.app.job;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.domain.ReservationStatus;
import tn.cita.app.domain.entity.Reservation;
import tn.cita.app.repository.ReservationRepository;

@Component
@Slf4j
@RequiredArgsConstructor
public class ReservationJobScheduler {
	
	private final ReservationRepository reservationRepository;
	
	/**
	 * Schedule daily update (at 00h:00min) for each NOT_STARTED reservation, switched to OUTDATED
	 */
	@Scheduled(cron = "0 0 0 * * *")
	@Transactional
	public void scheduleOudatedReservation() {
		
		final var from = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT);
		final var to = from.plusDays(1);
		
		final long updatedReservationsCount = this.reservationRepository
				.findAllByStatusAndStartDateBetween(ReservationStatus.NOT_STARTED, from, to).stream()
					.map(r -> {
						r.setStatus(ReservationStatus.OUTDATED);
						return r;
					})
					.map(this.reservationRepository::save)
					.peek(r -> log.info("** Reservation with code {} has been switched to {} **", 
							r.getCode(), r.getStatus()))
					.distinct()
					.sorted(Comparator.comparing(Reservation::getUpdatedAt))
					.count();
		log.info("All {} reservations has been oudated today {}", 
				updatedReservationsCount, LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
	}
	
	
	
}












