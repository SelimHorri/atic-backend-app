package tn.cita.app.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.constant.AppConstant;
import tn.cita.app.domain.id.FavouriteId;
import tn.cita.app.dto.CustomerDto;
import tn.cita.app.dto.request.ClientPageRequest;
import tn.cita.app.dto.response.CustomerContainerResponse;
import tn.cita.app.exception.wrapper.CustomerNotFoundException;
import tn.cita.app.mapper.CustomerMapper;
import tn.cita.app.repository.CustomerRepository;
import tn.cita.app.service.CustomerService;
import tn.cita.app.service.FavouriteService;
import tn.cita.app.service.RatingService;
import tn.cita.app.service.ReservationService;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
	
	private final CustomerRepository customerRepository;
	private final ReservationService reservationService;
	private final FavouriteService favouriteService;
	private final RatingService ratingService;
	
	@Override
	public Page<CustomerDto> findAll(final int pageOffset) {
		log.info("** CustomerServiceImpl; List CustomerDto; find All with pageOffset service...*\n");
		return this.customerRepository.findAll(PageRequest.of(pageOffset - 1, AppConstant.PAGE_SIZE))
				.map(CustomerMapper::map);
	}
	
	@Override
	public CustomerDto findById(final Integer id) {
		log.info("** CustomerServiceImpl; CustomerDto; find user by id service...*\n");
		return this.customerRepository.findById(id)
				.map(CustomerMapper::map)
				.orElseThrow(() -> new CustomerNotFoundException(String
						.format("Customer with id: %d not found", id)));
	}
	
	@Transactional
	@Override
	public boolean deleteById(final Integer id) {
		log.info("** CustomerServiceImpl; boolean; delete user by id service...*\n");
		this.customerRepository.deleteById(id);
		return !this.customerRepository.existsById(id);
	}
	
	@Override
	public CustomerDto findByCredentialUsernameIgnoringCase(final String username) {
		return this.customerRepository.findByCredentialUsernameIgnoringCase(username)
				.map(CustomerMapper::map)
				.orElseThrow(() -> new CustomerNotFoundException(String
						.format("Customer with username: %s not found", username)));
	}
	
	@Override
	public CustomerContainerResponse getProfileByUsername(final String username, final ClientPageRequest clientPageRequest) {
		
		final var customerDto = this.findByCredentialUsernameIgnoringCase(username);
		
		return new CustomerContainerResponse(
				this.findByCredentialUsernameIgnoringCase(username), 
				null, 
				this.reservationService.findAllByCustomerId(customerDto.getId(), clientPageRequest), 
				this.favouriteService.findAllByCustomerId(customerDto.getId(), clientPageRequest),
				this.ratingService.findAllByCustomerId(customerDto.getId()));
	}
	
	@Override
	public CustomerContainerResponse getFavouritesByUsername(final String username, final ClientPageRequest clientPageRequest) {
		
		final var customerDto = this.findByCredentialUsernameIgnoringCase(username);
		
		return CustomerContainerResponse.builder()
				.customerDto(customerDto)
				.favouriteDtos(this.favouriteService.findAllByCustomerId(customerDto.getId(), clientPageRequest))
				.build();
	}
	
	@Override
	public CustomerContainerResponse getReservationsByUsername(final String username, final ClientPageRequest clientPageRequest) {
		
		final var customerDto = this.findByCredentialUsernameIgnoringCase(username);
		
		return CustomerContainerResponse.builder()
				.customerDto(customerDto)
				.reservationDtos(this.reservationService.findAllByCustomerId(customerDto.getId(), clientPageRequest))
				.build();
	}
	
	@Override
	public CustomerContainerResponse getRatingsByUsername(final String username) {
		
		final var customerDto = this.findByCredentialUsernameIgnoringCase(username);
		
		return CustomerContainerResponse.builder()
				.customerDto(customerDto)
				.ratingDtos(this.ratingService.findAllByCustomerId(customerDto.getId()))
				.build();
	}
	
	@Transactional
	@Override
	public Boolean deleteFavourite(final String username, final Integer saloonId) {
		final var favouriteId = new FavouriteId(this.findByCredentialUsernameIgnoringCase(username).getId(), saloonId);
		return this.favouriteService.deleteById(favouriteId);
	}
	
	
	
}














