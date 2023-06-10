package tn.cita.app.business.category;

import tn.cita.app.business.category.employee.manager.model.CategoryRequest;
import tn.cita.app.model.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
	
	List<CategoryDto> findAll();
	CategoryDto findById(final Integer id);
	CategoryDto findByIdentifier(final String identifier);
	List<CategoryDto> findAllBySaloonId(final Integer saloonId);
	CategoryDto save(final CategoryRequest categoryRequest);
	CategoryDto update(final CategoryRequest categoryRequest);
	
}



