package tn.cita.app.service;

import java.util.List;

import tn.cita.app.domain.id.FavouriteId;
import tn.cita.app.dto.FavouriteDto;

public interface FavouriteService {
	
	List<FavouriteDto> findAllByCustomerId(final Integer customerId);
	Boolean deleteById(final FavouriteId favouriteId);
	
}








