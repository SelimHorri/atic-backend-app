package tn.cita.app.service.v0.business.customer.impl;

import org.springframework.data.domain.PageImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class CustomerProfileServiceImpl implements CustomerProfileService {
	
	private final CustomerService customerService;
	private final ReservationService reservationService;
	private final FavouriteService favouriteService;
	private final RatingService ratingService;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public CustomerProfileResponse fetchProfile(final String username, final ClientPageRequest clientPageRequest) {
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
		authenticatedCustomer.setFirstname(customerProfileRequest.getFirstname());
		authenticatedCustomer.setLastname(customerProfileRequest.getLastname());
		authenticatedCustomer.setEmail(customerProfileRequest.getEmail());
		authenticatedCustomer.setPhone(customerProfileRequest.getPhone());
		authenticatedCustomer.setBirthdate(customerProfileRequest.getBirthdate());
		authenticatedCustomer.setFacebookUrl(customerProfileRequest.getFacebookUrl());
		authenticatedCustomer.setInstagramUrl(customerProfileRequest.getInstagramUrl());
		authenticatedCustomer.setLinkedinUrl(customerProfileRequest.getLinkedinUrl());
		authenticatedCustomer.getCredential().setUsername(customerProfileRequest.getUsername().toLowerCase());
		authenticatedCustomer.getCredential().setPassword(this.passwordEncoder.encode(customerProfileRequest.getPassword()));
		
		return CustomerMapper.map(this.customerService.getCustomerRepository().save(authenticatedCustomer));
	}
	
	
	
}
















