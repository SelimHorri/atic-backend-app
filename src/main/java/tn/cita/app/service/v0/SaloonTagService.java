package tn.cita.app.service.v0;

import java.util.List;

import tn.cita.app.dto.SaloonTagDto;
import tn.cita.app.repository.SaloonTagRepository;

public interface SaloonTagService {
	
	SaloonTagRepository geSaloonTagRepository();
	List<SaloonTagDto> findAllBySaloonId(final Integer saloonId);
	
}











