package tn.cita.app.service.v0;

import java.util.List;

import tn.cita.app.dto.SaloonTagDto;

public interface SaloonTagService {
	
	List<SaloonTagDto> findAllBySaloonId(final Integer saloonId);
	
}











