package tn.cita.app.service.v0.business.customer.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tn.cita.app.dto.response.CustomerRatingResponse;
import tn.cita.app.service.v0.CustomerService;
import tn.cita.app.service.v0.RatingService;
import tn.cita.app.service.v0.business.customer.CustomerRatingService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomerRatingServiceImpl implements CustomerRatingService {
	
	private final CustomerService customerService;
	private final RatingService ratingService;
	
	@Override
	public CustomerRatingResponse getRatingsByUsername(final String username) {
		final var customerDto = this.customerService.findByCredentialUsername(username);
		return new CustomerRatingResponse(
				customerDto,
				this.ratingService.findAllByCustomerId(customerDto.getId()));
	}
	
	
	
}
















