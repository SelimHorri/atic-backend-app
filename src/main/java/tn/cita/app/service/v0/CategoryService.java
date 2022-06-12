package tn.cita.app.service.v0;

import java.util.List;

import tn.cita.app.dto.CategoryDto;

public interface CategoryService {
	
	List<CategoryDto> findAllBySaloonId(final Integer saloonId);
	
}










