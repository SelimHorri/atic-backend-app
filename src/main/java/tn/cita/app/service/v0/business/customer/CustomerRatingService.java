package tn.cita.app.service.v0.business.customer;

import tn.cita.app.model.dto.response.CustomerRatingResponse;

public interface CustomerRatingService {
	
	CustomerRatingResponse fetchAllRatings(final String username);
	
}








