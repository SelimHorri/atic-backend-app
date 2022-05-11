package tn.cita.app.service;

import java.util.List;

import tn.cita.app.dto.FavouriteDto;

public interface FavouriteService {
	
	List<FavouriteDto> findAllByCustomerId(final Integer customerId);
	
}








