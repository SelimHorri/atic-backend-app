package tn.cita.app.service.v0.business.customer.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.domain.id.FavouriteId;
import tn.cita.app.dto.request.ClientPageRequest;
import tn.cita.app.dto.response.CustomerFavouriteResponse;
import tn.cita.app.service.v0.CustomerService;
import tn.cita.app.service.v0.FavouriteService;
import tn.cita.app.service.v0.business.customer.CustomerFavouriteService;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class CustomerFavouriteServiceImpl implements CustomerFavouriteService {
	
	private final CustomerService customerService;
	private final FavouriteService favouriteService;
	
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
	
	
	
}














