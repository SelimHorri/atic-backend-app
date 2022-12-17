package tn.cita.app.service.v0.business.customer.impl;

import org.springframework.data.domain.PageImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.exception.wrapper.CustomerNotFoundException;
import tn.cita.app.exception.wrapper.PasswordNotMatchException;
import tn.cita.app.exception.wrapper.UsernameAlreadyExistsException;
import tn.cita.app.mapper.CustomerMapper;
import tn.cita.app.mapper.FavouriteMapper;
import tn.cita.app.mapper.RatingMapper;
import tn.cita.app.mapper.ReservationMapper;
import tn.cita.app.model.dto.CustomerDto;
import tn.cita.app.model.dto.request.ClientPageRequest;
import tn.cita.app.model.dto.request.CustomerProfileRequest;
import tn.cita.app.model.dto.response.CustomerProfileResponse;
import tn.cita.app.repository.CustomerRepository;
import tn.cita.app.repository.FavouriteRepository;
import tn.cita.app.repository.RatingRepository;
import tn.cita.app.repository.ReservationRepository;
import tn.cita.app.service.v0.business.customer.CustomerProfileService;
import tn.cita.app.util.ClientPageRequestUtils;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class CustomerProfileServiceImpl implements CustomerProfileService {
	
	private final CustomerRepository customerRepository;
	private final ReservationRepository reservationRepository;
	private final FavouriteRepository favouriteRepository;
	private final RatingRepository ratingRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public CustomerProfileResponse fetchProfile(final String username, final ClientPageRequest clientPageRequest) {
		log.info("** Fetch customer profile.. *\n");
		final var customerDto = this.customerRepository
				.findByCredentialUsernameIgnoringCase(username)
				.map(CustomerMapper::map)
				.orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
		
		return CustomerProfileResponse.builder()
				.customerDto(customerDto)
				.reservationDtos(this.reservationRepository.findAllByCustomerId(customerDto.getId(), 
						ClientPageRequestUtils.from(clientPageRequest))
					.map(ReservationMapper::map))
				.favouriteDtos(this.favouriteRepository.findAllByCustomerId(customerDto.getId(), 
						ClientPageRequestUtils.from(clientPageRequest))
					.map(FavouriteMapper::map))
				.ratingDtos(new PageImpl<>(this.ratingRepository
						.findAllByCustomerId(customerDto.getId()).stream()
							.map(RatingMapper::map)
							.distinct()
							.toList()))
				.build();
	}
	
	@Transactional
	@Override
	public CustomerDto updateProfileInfo(final CustomerProfileRequest customerProfileRequest) {
		
		log.info("** Update customer profile.. *\n");
		
		this.customerRepository
				.findByCredentialUsernameIgnoringCase(customerProfileRequest.username().strip()).ifPresent(c -> {
			if (!c.getCredential().getUsername().equals(customerProfileRequest.authenticatedUsername()))
				throw new UsernameAlreadyExistsException("Username already exists, please choose another");
		});
		
		if (!customerProfileRequest.password().equals(customerProfileRequest.confirmPassword()))
			throw new PasswordNotMatchException("Passwords are not matched.. please confirm");
		
		final var authenticatedCustomer = this.customerRepository
				.findByCredentialUsernameIgnoringCase(customerProfileRequest.authenticatedUsername())
				.orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
		authenticatedCustomer.setFirstname(customerProfileRequest.firstname().strip());
		authenticatedCustomer.setLastname(customerProfileRequest.lastname().strip());
		authenticatedCustomer.setEmail(customerProfileRequest.email().strip());
		authenticatedCustomer.setPhone(customerProfileRequest.phone().strip());
		authenticatedCustomer.setBirthdate(customerProfileRequest.birthdate());
		authenticatedCustomer.setFacebookUrl(StringUtils.trimAllWhitespace(customerProfileRequest.facebookUrl()));
		authenticatedCustomer.setInstagramUrl(StringUtils.trimAllWhitespace(customerProfileRequest.instagramUrl()));
		authenticatedCustomer.setLinkedinUrl(StringUtils.trimAllWhitespace(customerProfileRequest.linkedinUrl()));
		authenticatedCustomer.getCredential().setUsername(customerProfileRequest.username().strip().toLowerCase());
		authenticatedCustomer.getCredential().setPassword(this.passwordEncoder.encode(customerProfileRequest.password()));
		
		return CustomerMapper.map(this.customerRepository.save(authenticatedCustomer));
	}
	
	
	
}
















