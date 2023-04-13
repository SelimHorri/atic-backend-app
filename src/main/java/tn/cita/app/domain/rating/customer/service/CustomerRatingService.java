package tn.cita.app.domain.rating.customer.service;

import tn.cita.app.domain.rating.customer.model.CustomerRatingResponse;

public interface CustomerRatingService {
	
	CustomerRatingResponse fetchAllRatings(final String username);
	
}





