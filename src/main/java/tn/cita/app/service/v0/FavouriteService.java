package tn.cita.app.service.v0;

import org.springframework.data.domain.Page;

import tn.cita.app.domain.id.FavouriteId;
import tn.cita.app.dto.FavouriteDto;
import tn.cita.app.dto.request.ClientPageRequest;
import tn.cita.app.repository.FavouriteRepository;

public interface FavouriteService {
	
	FavouriteRepository getFavouriteRepository();
	FavouriteDto findById(final FavouriteId favouriteId);
	Page<FavouriteDto> findAllByCustomerId(final Integer customerId, final ClientPageRequest clientPageRequest);
	Boolean deleteById(final FavouriteId favouriteId);
	
}








