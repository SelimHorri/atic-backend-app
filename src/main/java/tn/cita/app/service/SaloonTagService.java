package tn.cita.app.service;

import tn.cita.app.model.dto.SaloonTagDto;

import java.util.List;

public interface SaloonTagService {
	
	List<SaloonTagDto> findAllBySaloonId(final Integer saloonId);
	
}


