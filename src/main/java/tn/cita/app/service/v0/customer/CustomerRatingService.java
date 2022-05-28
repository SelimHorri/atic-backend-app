package tn.cita.app.service.v0.customer;

import tn.cita.app.dto.response.CustomerRatingResponse;

public interface CustomerRatingService {
	
	CustomerRatingResponse getRatingsByUsername(final String username);
	
}








