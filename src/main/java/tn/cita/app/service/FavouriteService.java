package tn.cita.app.service;

import org.springframework.data.domain.Page;
import tn.cita.app.model.domain.id.FavouriteId;
import tn.cita.app.model.dto.FavouriteDto;
import tn.cita.app.model.dto.request.ClientPageRequest;

public interface FavouriteService {
	
	FavouriteDto findById(final FavouriteId favouriteId);
	Page<FavouriteDto> findAllByCustomerId(final Integer customerId, final ClientPageRequest clientPageRequest);
	Boolean deleteById(final FavouriteId favouriteId);
	
}



