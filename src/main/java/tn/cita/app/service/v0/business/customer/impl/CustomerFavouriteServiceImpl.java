package tn.cita.app.service.v0.business.customer.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.domain.entity.Favourite;
import tn.cita.app.domain.id.FavouriteId;
import tn.cita.app.dto.FavouriteDto;
import tn.cita.app.dto.request.ClientPageRequest;
import tn.cita.app.dto.response.CustomerFavouriteResponse;
import tn.cita.app.exception.wrapper.CustomerNotFoundException;
import tn.cita.app.exception.wrapper.FavouriteAlreadyExists;
import tn.cita.app.exception.wrapper.FavouriteNotFoundException;
import tn.cita.app.exception.wrapper.SaloonNotFoundException;
import tn.cita.app.mapper.CustomerMapper;
import tn.cita.app.mapper.FavouriteMapper;
import tn.cita.app.repository.CustomerRepository;
import tn.cita.app.repository.FavouriteRepository;
import tn.cita.app.repository.SaloonRepository;
import tn.cita.app.service.v0.business.customer.CustomerFavouriteService;
import tn.cita.app.util.ClientPageRequestUtils;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class CustomerFavouriteServiceImpl implements CustomerFavouriteService {
	
	private final CustomerRepository customerRepository;
	private final FavouriteRepository favouriteRepository;
	private final SaloonRepository saloonRepository;
	
	@Override
	public CustomerFavouriteResponse fetchAllFavourites(final String username, final ClientPageRequest clientPageRequest) {
		log.info("** Fetch all favourites by customer.. *\n");
		final var customerDto = this.customerRepository
				.findByCredentialUsernameIgnoringCase(username)
				.map(CustomerMapper::map)
				.orElseThrow(() -> new CustomerNotFoundException(String
						.format("Customer with username: %s not found", username)));
		return new CustomerFavouriteResponse(
				customerDto,
				this.favouriteRepository.findAllByCustomerId(customerDto.getId(), 
						ClientPageRequestUtils.from(clientPageRequest))
					.map(FavouriteMapper::map));
	}
	
	@Transactional
	@Override
	public Boolean deleteFavourite(final String username, final Integer saloonId) {
		log.info("** Delete favourite by customer.. *\n");
		final var favouriteId = new FavouriteId(this.customerRepository
				.findByCredentialUsernameIgnoringCase(username)
				.map(CustomerMapper::map)
				.orElseThrow(() -> new CustomerNotFoundException(String
						.format("Customer with username: %s not found", username)))
				.getId(), 
				saloonId);
		this.favouriteRepository.deleteById(favouriteId);
		return !this.favouriteRepository.existsById(favouriteId);
	}
	
	@Transactional
	@Override
	public FavouriteDto addFavourite(final String username, final Integer saloonId) {
		
		log.info("** Add new favourite by customer.. *\n");
		
		final var customer = this.customerRepository.findByCredentialUsernameIgnoringCase(username)
				.orElseThrow(() -> new CustomerNotFoundException("Customer with username %s not found".formatted(username)));
		
		// Check if this favourite already exists..
		final var favouriteId = new FavouriteId(customer.getId(), saloonId);
		this.favouriteRepository.findById(favouriteId).ifPresent(f -> {
				throw new FavouriteAlreadyExists("This is already part of your favourites");
		});
		
		// persist..
		this.favouriteRepository.saveFavourite(Favourite.builder()
					.customerId(customer.getId())
					.saloonId(saloonId)
					.favouriteDate(LocalDateTime.now())
					.identifier(UUID.randomUUID().toString())
					.customer(customer)
					.saloon(this.saloonRepository.findById(saloonId)
							.orElseThrow(() -> new SaloonNotFoundException("Saloon not found")))
					.build());
		
		return this.favouriteRepository.findById(favouriteId)
				.map(FavouriteMapper::map)
				.orElseThrow(() -> new FavouriteNotFoundException("Favourite not found"));
	}
	
	
	
}














