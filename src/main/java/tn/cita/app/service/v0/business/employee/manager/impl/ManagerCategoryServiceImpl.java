package tn.cita.app.service.v0.business.employee.manager.impl;

import java.util.Comparator;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.dto.CategoryDto;
import tn.cita.app.dto.request.CategoryRequest;
import tn.cita.app.service.v0.CategoryService;
import tn.cita.app.service.v0.EmployeeService;
import tn.cita.app.service.v0.business.employee.manager.ManagerCategoryService;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class ManagerCategoryServiceImpl implements ManagerCategoryService {
	
	private final EmployeeService employeeService;
	private final CategoryService categoryService;
	
	@Override
	public Page<CategoryDto> fetchAll(final String username) {
		log.info("** Fetch all categories by manager.. *\n");
		return new PageImpl<>(this.categoryService
				.findAllBySaloonId(this.employeeService
						.findByCredentialUsername(username).getSaloonDto().getId()).stream()
					.distinct()
					.sorted(Comparator.comparing(CategoryDto::getName))
					.collect(Collectors.toUnmodifiableList()));
	}
	
	@Override
	public CategoryDto fetchById(final Integer categoryId) {
		log.info("** Fetch category by id by manager.. *\n");
		return this.categoryService.findById(categoryId);
	}
	
	@Transactional
	@Override
	public Boolean deleteCategory(final Integer categoryId) {
		log.info("** Delete category by id by manager.. *\n");
		this.categoryService.getCategoryRepository().deleteById(categoryId);
		return !this.categoryService.getCategoryRepository().existsById(categoryId);
	}
	
	@Transactional
	@Override
	public CategoryDto saveCategory(final CategoryRequest categoryRequest) {
		log.info("** Save category by manager.. *\n");
		return this.categoryService.save(categoryRequest);
	}
	
	@Transactional
	@Override
	public CategoryDto updateCategory(final CategoryRequest categoryRequest) {
		log.info("** Update category by manager.. *\n");
		return this.categoryService.update(categoryRequest);
	}
	
	
	
}

















