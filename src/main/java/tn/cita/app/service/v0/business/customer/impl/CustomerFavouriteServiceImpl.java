package tn.cita.app.service.v0.business.customer.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tn.cita.app.domain.id.FavouriteId;
import tn.cita.app.dto.request.ClientPageRequest;
import tn.cita.app.dto.response.CustomerFavouriteResponse;
import tn.cita.app.service.v0.CustomerService;
import tn.cita.app.service.v0.FavouriteService;
import tn.cita.app.service.v0.business.customer.CustomerFavouriteService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomerFavouriteServiceImpl implements CustomerFavouriteService {
	
	private final CustomerService customerService;
	private final FavouriteService favouriteService;
	
	@Override
	public CustomerFavouriteResponse getFavouritesByUsername(final String username, final ClientPageRequest clientPageRequest) {
		final var customerDto = this.customerService.findByCredentialUsername(username);
		return new CustomerFavouriteResponse(
				customerDto,
				this.favouriteService.findAllByCustomerId(customerDto.getId(), clientPageRequest));
	}
	
	@Transactional
	@Override
	public Boolean deleteFavourite(final String username, final Integer saloonId) {
		final var favouriteId = new FavouriteId(this.customerService.findByCredentialUsername(username).getId(), saloonId);
		return this.favouriteService.deleteById(favouriteId);
	}
	
	
	
}














