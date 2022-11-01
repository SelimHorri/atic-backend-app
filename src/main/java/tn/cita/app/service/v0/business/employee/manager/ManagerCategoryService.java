package tn.cita.app.service.v0.business.employee.manager;

import org.springframework.data.domain.Page;

import tn.cita.app.model.dto.CategoryDto;
import tn.cita.app.model.dto.request.CategoryRequest;

public interface ManagerCategoryService {
	
	Page<CategoryDto> fetchAll(final String username);
	CategoryDto fetchById(final Integer categoryId);
	Boolean deleteCategory(final Integer categoryId);
	CategoryDto saveCategory(final CategoryRequest categoryRequest);
	CategoryDto updateCategory(final CategoryRequest categoryRequest);
	
}











