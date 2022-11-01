package tn.cita.app.service.v0.business.customer;

import tn.cita.app.model.dto.FavouriteDto;
import tn.cita.app.model.dto.request.ClientPageRequest;
import tn.cita.app.model.dto.response.CustomerFavouriteResponse;

public interface CustomerFavouriteService {
	
	CustomerFavouriteResponse fetchAllFavourites(final String username, final ClientPageRequest clientPageRequest);
	Boolean deleteFavourite(final String username, final Integer saloonId);
	FavouriteDto addFavourite(final String username, final Integer saloonId);
	
}








