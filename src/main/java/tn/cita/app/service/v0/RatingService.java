package tn.cita.app.service.v0;

import org.springframework.data.domain.Page;

import tn.cita.app.dto.RatingDto;

public interface RatingService {
	
	Page<RatingDto> findAllByCustomerId(final Integer customerId);
	
}









