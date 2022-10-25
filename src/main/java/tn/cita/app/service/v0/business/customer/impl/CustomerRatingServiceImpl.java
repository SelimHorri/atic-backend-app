package tn.cita.app.service.v0.business.customer.impl;

import java.util.stream.Collectors;

import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.dto.response.CustomerRatingResponse;
import tn.cita.app.exception.wrapper.CustomerNotFoundException;
import tn.cita.app.mapper.CustomerMapper;
import tn.cita.app.mapper.RatingMapper;
import tn.cita.app.repository.CustomerRepository;
import tn.cita.app.repository.RatingRepository;
import tn.cita.app.service.v0.business.customer.CustomerRatingService;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class CustomerRatingServiceImpl implements CustomerRatingService {
	
	private final CustomerRepository customerRepository;
	private final RatingRepository ratingRepository;
	
	@Override
	public CustomerRatingResponse fetchAllRatings(final String username) {
		log.info("** Fetch all ratings by customer.. *\n");
		final var customerDto = this.customerRepository
				.findByCredentialUsernameIgnoringCase(username)
				.map(CustomerMapper::map)
				.orElseThrow(() -> new CustomerNotFoundException("Customer with username: %s not found".formatted(username)));
		return new CustomerRatingResponse(
				customerDto,
				new PageImpl<>(this.ratingRepository.findAllByCustomerId(customerDto.getId()).stream()
						.map(RatingMapper::map)
						.distinct()
						.collect(Collectors.toUnmodifiableList())));
	}
	
	
	
}
















