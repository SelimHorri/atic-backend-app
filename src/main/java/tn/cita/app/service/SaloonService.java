package tn.cita.app.service;

import org.springframework.data.domain.Page;

import tn.cita.app.dto.SaloonDto;

public interface SaloonService {
	
	Page<SaloonDto> findAll(final int offset);
	Page<SaloonDto> findAllByLocationState(final String state, final int offset);
	SaloonDto findById(final Integer id);
	Page<SaloonDto> findAllByCode(final String code);
	
}












