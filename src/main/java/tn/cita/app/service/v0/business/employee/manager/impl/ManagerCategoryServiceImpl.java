package tn.cita.app.service.v0.business.employee.manager.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tn.cita.app.dto.CategoryDto;
import tn.cita.app.service.v0.CategoryService;
import tn.cita.app.service.v0.EmployeeService;
import tn.cita.app.service.v0.business.employee.manager.ManagerCategoryService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ManagerCategoryServiceImpl implements ManagerCategoryService {
	
	private final EmployeeService employeeService;
	private final CategoryService categoryService;
	
	@Override
	public Page<CategoryDto> getAll(final String username) {
		final var managerDto = this.employeeService.findByUsername(username);
		return new PageImpl<>(this.categoryService.findAllBySaloonId(managerDto.getSaloonDto().getId()));
	}
	
	@Override
	public CategoryDto getById(final Integer categoryId) {
		return this.categoryService.findById(categoryId);
	}
	
	@Transactional
	@Override
	public Boolean deleteCategory(final Integer categoryId) {
		this.categoryService.getCategoryRepository().deleteById(categoryId);
		return !this.categoryService.getCategoryRepository().existsById(categoryId);
	}
	
	
	
}

















