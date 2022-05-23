package tn.cita.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import tn.cita.app.domain.entity.Rating;
import tn.cita.app.domain.id.RatingId;

public interface RatingRepository extends JpaRepository<Rating, RatingId> {
	
	Page<Rating> findAllByCustomerId(final Integer customerId, final Pageable pageable);
	Page<Rating> findAllByWorkerId(final Integer workerId, final Pageable pageable);
	
}








