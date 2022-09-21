package tn.cita.app.service.v0.business.customer.impl;

import org.springframework.data.domain.PageImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.dto.CustomerDto;
import tn.cita.app.dto.request.ClientPageRequest;
import tn.cita.app.dto.request.CustomerProfileRequest;
import tn.cita.app.dto.response.CustomerProfileResponse;
import tn.cita.app.exception.wrapper.PasswordNotMatchException;
import tn.cita.app.exception.wrapper.UsernameAlreadyExistsException;
import tn.cita.app.mapper.CustomerMapper;
import tn.cita.app.service.v0.CustomerService;
import tn.cita.app.service.v0.FavouriteService;
import tn.cita.app.service.v0.RatingService;
import tn.cita.app.service.v0.ReservationService;
import tn.cita.app.service.v0.business.customer.CustomerProfileService;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class CustomerProfileServiceImpl implements CustomerProfileService {
	
	private final CustomerService customerService;
	private final ReservationService reservationService;
	private final FavouriteService favouriteService;
	private final RatingService ratingService;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public CustomerProfileResponse fetchProfile(final String username, final ClientPageRequest clientPageRequest) {
		log.info("** Fetch customer profile.. *\n");
		final var customerDto = this.customerService.findByCredentialUsername(username);
		return new CustomerProfileResponse(
				customerDto, 
				null, 
				this.reservationService.findAllByCustomerId(customerDto.getId(), clientPageRequest), 
				this.favouriteService.findAllByCustomerId(customerDto.getId(), clientPageRequest),
				new PageImpl<>(this.ratingService.findAllByCustomerId(customerDto.getId())));
	}
	
	@Transactional
	@Override
	public CustomerDto updateProfileInfo(final CustomerProfileRequest customerProfileRequest) {
		
		log.info("** Update customer profile.. *\n");
		
		this.customerService.getCustomerRepository()
				.findByCredentialUsernameIgnoringCase(customerProfileRequest.getUsername().strip()).ifPresent(c -> {
			if (!c.getCredential().getUsername().equals(customerProfileRequest.getAuthenticatedUsername()))
				throw new UsernameAlreadyExistsException("Username already exists, please choose another");
		});
		
		if (!customerProfileRequest.getPassword().equals(customerProfileRequest.getConfirmPassword()))
			throw new PasswordNotMatchException("Passwords are not matched.. please confirm");
		
		final var authenticatedCustomer = this.customerService.getCustomerRepository()
				.findByCredentialUsernameIgnoringCase(customerProfileRequest.getAuthenticatedUsername())
				.orElseThrow();
		authenticatedCustomer.setFirstname(customerProfileRequest.getFirstname().strip());
		authenticatedCustomer.setLastname(customerProfileRequest.getLastname().strip());
		authenticatedCustomer.setEmail(customerProfileRequest.getEmail().strip());
		authenticatedCustomer.setPhone(customerProfileRequest.getPhone().strip());
		authenticatedCustomer.setBirthdate(customerProfileRequest.getBirthdate());
		authenticatedCustomer.setFacebookUrl(customerProfileRequest.getFacebookUrl().strip());
		authenticatedCustomer.setInstagramUrl(customerProfileRequest.getInstagramUrl().strip());
		authenticatedCustomer.setLinkedinUrl(customerProfileRequest.getLinkedinUrl().strip());
		authenticatedCustomer.getCredential().setUsername(customerProfileRequest.getUsername().strip().toLowerCase());
		authenticatedCustomer.getCredential().setPassword(this.passwordEncoder.encode(customerProfileRequest.getPassword()));
		
		return CustomerMapper.map(this.customerService.getCustomerRepository().save(authenticatedCustomer));
	}
	
	
	
}
















