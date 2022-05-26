package tn.cita.app.service;

import org.springframework.data.domain.Page;

import tn.cita.app.domain.id.FavouriteId;
import tn.cita.app.dto.FavouriteDto;

public interface FavouriteService {
	
	Page<FavouriteDto> findAllByCustomerId(final Integer customerId);
	Page<FavouriteDto> findAllByCustomerId(final Integer customerId, final int offset, final int size);
	Boolean deleteById(final FavouriteId favouriteId);
	
}








