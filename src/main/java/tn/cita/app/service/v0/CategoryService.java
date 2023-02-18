package tn.cita.app.service.v0;

import java.util.List;

import tn.cita.app.model.dto.CategoryDto;
import tn.cita.app.model.dto.request.CategoryRequest;

public interface CategoryService {
	
	List<CategoryDto> findAll();
	CategoryDto findById(final Integer id);
	CategoryDto findByIdentifier(final String identifier);
	List<CategoryDto> findAllBySaloonId(final Integer saloonId);
	CategoryDto save(final CategoryRequest categoryRequest);
	CategoryDto update(final CategoryRequest categoryRequest);
	
}






