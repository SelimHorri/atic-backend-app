package tn.cita.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.cita.app.domain.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
	
	List<Reservation> findAllByCustomerId(final Integer customerId);
	Optional<Reservation> findByCode(final String code);
	
}










