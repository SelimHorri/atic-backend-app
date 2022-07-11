package tn.cita.app.service.v0.business.employee.manager;

import org.springframework.data.domain.Page;

import tn.cita.app.dto.CategoryDto;
import tn.cita.app.dto.request.CategoryRequest;

public interface ManagerCategoryService {
	
	Page<CategoryDto> getAll(final String username);
	CategoryDto getById(final Integer categoryId);
	Boolean deleteCategory(final Integer categoryId);
	CategoryDto saveCategory(final CategoryRequest categoryRequest);
	CategoryDto updateCategory(final CategoryRequest categoryRequest);
	
}











