package tn.cita.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import tn.cita.app.domain.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
	
	Page<Reservation> findAllByCustomerId(final Integer customerId, final Pageable pageable);
	Optional<Reservation> findByCode(final String code);
	Page<Reservation> findAllBySaloonId(final Integer saloonId, final Pageable pageable);
	List<Reservation> findAllBySaloonId(final Integer saloonId);
	
}










