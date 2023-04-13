package tn.cita.app.domain.category;

import java.util.List;

import tn.cita.app.domain.category.employee.manager.model.CategoryRequest;
import tn.cita.app.model.dto.CategoryDto;

public interface CategoryService {
	
	List<CategoryDto> findAll();
	CategoryDto findById(final Integer id);
	CategoryDto findByIdentifier(final String identifier);
	List<CategoryDto> findAllBySaloonId(final Integer saloonId);
	CategoryDto save(final CategoryRequest categoryRequest);
	CategoryDto update(final CategoryRequest categoryRequest);
	
}






