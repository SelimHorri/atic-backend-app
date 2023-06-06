package tn.cita.app.business.favourite.customer.service;

import tn.cita.app.business.favourite.customer.model.CustomerFavouriteResponse;
import tn.cita.app.model.dto.FavouriteDto;
import tn.cita.app.model.dto.request.ClientPageRequest;

public interface CustomerFavouriteService {
	
	CustomerFavouriteResponse fetchAllFavourites(final String username, final ClientPageRequest clientPageRequest);
	Boolean deleteFavourite(final String username, final Integer saloonId);
	FavouriteDto addFavourite(final String username, final Integer saloonId);
	
}



