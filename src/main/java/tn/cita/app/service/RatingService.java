package tn.cita.app.service;

import tn.cita.app.model.dto.RatingDto;

import java.util.List;

public interface RatingService {
	
	List<RatingDto> findAllByCustomerId(final Integer customerId);
	
}


