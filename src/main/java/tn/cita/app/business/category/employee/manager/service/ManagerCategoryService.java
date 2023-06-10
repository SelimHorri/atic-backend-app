package tn.cita.app.business.category.employee.manager.service;

import org.springframework.data.domain.Page;
import tn.cita.app.business.category.employee.manager.model.CategoryRequest;
import tn.cita.app.model.dto.CategoryDto;

public interface ManagerCategoryService {
	
	Page<CategoryDto> fetchAll(final String username);
	CategoryDto fetchById(final Integer categoryId);
	Boolean deleteCategory(final Integer categoryId);
	CategoryDto saveCategory(final CategoryRequest categoryRequest);
	CategoryDto updateCategory(final CategoryRequest categoryRequest);
	
}



