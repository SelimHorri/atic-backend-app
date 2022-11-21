package tn.cita.app.service.v0.business.employee.manager.impl;

import java.util.Comparator;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.exception.wrapper.CategoryNotFoundException;
import tn.cita.app.exception.wrapper.EmployeeNotFoundException;
import tn.cita.app.exception.wrapper.SaloonNotFoundException;
import tn.cita.app.mapper.CategoryMapper;
import tn.cita.app.mapper.EmployeeMapper;
import tn.cita.app.model.domain.entity.Category;
import tn.cita.app.model.dto.CategoryDto;
import tn.cita.app.model.dto.request.CategoryRequest;
import tn.cita.app.repository.CategoryRepository;
import tn.cita.app.repository.EmployeeRepository;
import tn.cita.app.repository.SaloonRepository;
import tn.cita.app.service.v0.business.employee.manager.ManagerCategoryService;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class ManagerCategoryServiceImpl implements ManagerCategoryService {
	
	private final EmployeeRepository employeeRepository;
	private final CategoryRepository categoryRepository;
	private final SaloonRepository saloonRepository;
	
	@Override
	public Page<CategoryDto> fetchAll(final String username) {
		
		log.info("** Fetch all categories by manager.. *\n");
		
		final var managerDto = this.employeeRepository.findByCredentialUsernameIgnoringCase(username.strip())
				.map(EmployeeMapper::map)
				.orElseThrow(() -> new EmployeeNotFoundException(String
						.format("Employee with username: %s not found", username)));
		
		return new PageImpl<>(this.categoryRepository
				.findAllBySaloonId(managerDto.getSaloonDto().getId()).stream()
					.map(CategoryMapper::map)
					.distinct()
					.sorted(Comparator.comparing(CategoryDto::getName))
					.toList());
	}
	
	@Override
	public CategoryDto fetchById(final Integer categoryId) {
		log.info("** Fetch category by id by manager.. *\n");
		return this.categoryRepository.findById(categoryId)
				.map(CategoryMapper::map)
				.orElseThrow(() -> new CategoryNotFoundException("Category not found"));
	}
	
	@Transactional
	@Override
	public Boolean deleteCategory(final Integer categoryId) {
		log.info("** Delete category by id by manager.. *\n");
		this.categoryRepository.deleteById(categoryId);
		return !this.categoryRepository.existsById(categoryId);
	}
	
	@Transactional
	@Override
	public CategoryDto saveCategory(final CategoryRequest categoryRequest) {
		
		log.info("** Save category by manager.. *\n");
		
		final var parentCategory = (categoryRequest.parentCategoryId() != null) ?
				this.categoryRepository.findById(categoryRequest.parentCategoryId()).orElseGet(Category::new) : null;
		
		final var saloon = this.saloonRepository.findById(categoryRequest.saloonId())
				.orElseThrow(SaloonNotFoundException::new);
		
		final var category = Category.builder()
				.name(categoryRequest.name().strip().toLowerCase())
				.parentCategory(parentCategory)
				.saloon(saloon)
				.build();
		
		return CategoryMapper.map(this.categoryRepository.save(category));
	}
	
	@Transactional
	@Override
	public CategoryDto updateCategory(final CategoryRequest categoryRequest) {
		
		log.info("** Update category by manager.. *\n");
		
		final var parentCategory = (categoryRequest.parentCategoryId() != null) ?
				this.categoryRepository.findById(categoryRequest.parentCategoryId()).orElseGet(Category::new) : null;
		
		final var saloon = this.saloonRepository.findById(categoryRequest.saloonId())
				.orElseThrow(SaloonNotFoundException::new);
		
		final var category = this.categoryRepository.findById(categoryRequest.categoryId())
				.orElseThrow(CategoryNotFoundException::new);
		category.setId(categoryRequest.categoryId());
		category.setName(categoryRequest.name().strip().toLowerCase());
		category.setParentCategory(parentCategory);
		category.setSaloon(saloon);
		
		return CategoryMapper.map(this.categoryRepository.save(category));
	}
	
	
	
}

















