package tn.cita.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.cita.app.domain.entity.Rating;
import tn.cita.app.domain.id.RatingId;

public interface RatingRepository extends JpaRepository<Rating, RatingId> {
	
	
	
}








