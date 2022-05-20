package tn.cita.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.cita.app.domain.entity.Rating;
import tn.cita.app.domain.id.RatingId;

public interface RatingRepository extends JpaRepository<Rating, RatingId> {
	
	List<Rating> findAllByCustomerId(final Integer customerId);
	List<Rating> findAllByWorkerId(final Integer workerId);
	
}








