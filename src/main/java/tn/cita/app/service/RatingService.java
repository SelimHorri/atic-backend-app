package tn.cita.app.service;

import java.util.List;

import tn.cita.app.model.dto.RatingDto;

public interface RatingService {
	
	List<RatingDto> findAllByCustomerId(final Integer customerId);
	
}




