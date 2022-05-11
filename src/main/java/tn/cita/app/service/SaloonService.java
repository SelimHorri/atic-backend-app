package tn.cita.app.service;

import java.util.List;

import tn.cita.app.dto.SaloonDto;

public interface SaloonService {
	
	List<SaloonDto> findAll(final int offset);
	SaloonDto findById(final Integer id);
	List<SaloonDto> findAllByCode(final String code);
	
}












