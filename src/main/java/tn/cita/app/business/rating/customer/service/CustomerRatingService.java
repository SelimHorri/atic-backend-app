package tn.cita.app.business.rating.customer.service;

import tn.cita.app.business.rating.customer.model.CustomerRatingResponse;

public interface CustomerRatingService {
	
	CustomerRatingResponse fetchAllRatings(final String username);
	
}



