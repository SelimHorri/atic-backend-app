package tn.cita.app.service.v0.business.customer.impl;

import java.time.LocalDateTime;

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
import tn.cita.app.exception.wrapper.SaloonNotFoundException;
import tn.cita.app.service.v0.CustomerService;
import tn.cita.app.service.v0.FavouriteService;
import tn.cita.app.service.v0.SaloonService;
import tn.cita.app.service.v0.business.customer.CustomerFavouriteService;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class CustomerFavouriteServiceImpl implements CustomerFavouriteService {
	
	private final CustomerService customerService;
	private final FavouriteService favouriteService;
	private final SaloonService saloonService;
	
	@Override
	public CustomerFavouriteResponse fetchAllFavourites(final String username, final ClientPageRequest clientPageRequest) {
		log.info("** Fetch all favourites by customer.. *\n");
		final var customerDto = this.customerService.findByCredentialUsername(username);
		return new CustomerFavouriteResponse(
				customerDto,
				this.favouriteService.findAllByCustomerId(customerDto.getId(), clientPageRequest));
	}
	
	@Transactional
	@Override
	public Boolean deleteFavourite(final String username, final Integer saloonId) {
		log.info("** Delete favourite by customer.. *\n");
		final var favouriteId = new FavouriteId(this.customerService.findByCredentialUsername(username).getId(), saloonId);
		return this.favouriteService.deleteById(favouriteId);
	}
	
	@Transactional
	@Override
	public FavouriteDto addFavourite(final String username, final Integer saloonId) {
		
		log.info("** Add new favourite by customer.. *\n");
		
		final var customer = this.customerService.getCustomerRepository()
				.findByCredentialUsernameIgnoringCase(username)
				.orElseThrow(() -> new CustomerNotFoundException("Customer with username %s not found".formatted(username)));
		
		// Check if this favourite already exists..
		final var favouriteId = new FavouriteId(customer.getId(), saloonId);
		this.favouriteService.getFavouriteRepository()
				.findById(favouriteId).ifPresent(f -> {
					throw new FavouriteAlreadyExists("This is already part of your favourites");
		});
		
		// persist..
		this.favouriteService.getFavouriteRepository()
				.saveFavourite(Favourite.builder()
						.customerId(customer.getId())
						.saloonId(saloonId)
						.favouriteDate(LocalDateTime.now())
						.customer(customer)
						.saloon(this.saloonService.getSaloonRepository()
								.findById(saloonId)
								.orElseThrow(() -> new SaloonNotFoundException("Saloon not found")))
						.build());
		
		return this.favouriteService.findById(favouriteId);
	}
	
	
	
}














