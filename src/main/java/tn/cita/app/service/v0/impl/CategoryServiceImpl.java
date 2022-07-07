package tn.cita.app.service.v0.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tn.cita.app.domain.entity.Category;
import tn.cita.app.dto.CategoryDto;
import tn.cita.app.dto.request.CategoryRequest;
import tn.cita.app.exception.wrapper.CategoryNotFoundException;
import tn.cita.app.exception.wrapper.SaloonNotFoundException;
import tn.cita.app.mapper.CategoryMapper;
import tn.cita.app.repository.CategoryRepository;
import tn.cita.app.service.v0.CategoryService;
import tn.cita.app.service.v0.SaloonService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
	
	private final CategoryRepository categoryRepository;
	private final SaloonService saloonService;
	
	@Override
	public CategoryRepository getCategoryRepository() {
		return this.categoryRepository;
	}
	
	@Override
	public List<CategoryDto> findAll() {
		return this.categoryRepository.findAll().stream()
				.map(CategoryMapper::map)
				.distinct()
				.collect(Collectors.toUnmodifiableList());
	}
	
	@Override
	public CategoryDto findById(final Integer id) {
		return this.categoryRepository.findById(id)
				.map(CategoryMapper::map)
				.orElseThrow(() -> new CategoryNotFoundException("Category not found"));
	}
	
	@Override
	public List<CategoryDto> findAllBySaloonId(final Integer saloonId) {
		return this.categoryRepository.findAllBySaloonId(saloonId).stream()
				.map(CategoryMapper::map)
				.distinct()
				.collect(Collectors.toUnmodifiableList());
	}
	
	@Transactional
	@Override
	public CategoryDto save(final CategoryRequest categoryRequest) {
		
		final var parentCategory = this.categoryRepository
				.findById(categoryRequest.getParentCategoryId())
					.orElse(null);
		final var saloon = this.saloonService.getSaloonRepository()
				.findById(categoryRequest.getSaloonId())
					.orElseThrow(SaloonNotFoundException::new);
		
		final var category = Category.builder()
				.name(categoryRequest.getName().toLowerCase())
				.parentCategory(parentCategory)
				.saloon(saloon)
				.build();
		
		return CategoryMapper.map(this.categoryRepository.save(category));
	}
	
	@Transactional
	@Override
	public CategoryDto update(final CategoryRequest categoryRequest) {
		
		final var parentCategory = this.categoryRepository
				.findById(categoryRequest.getParentCategoryId())
					.orElse(null);
		final var saloon = this.saloonService.getSaloonRepository()
				.findById(categoryRequest.getSaloonId())
					.orElseThrow(SaloonNotFoundException::new);
		
		final var category = this.categoryRepository
				.findById(categoryRequest.getCategoryId())
					.orElseThrow(CategoryNotFoundException::new);
		category.setName(categoryRequest.getName().toLowerCase());
		category.setParentCategory(parentCategory);
		category.setSaloon(saloon);
		
		return CategoryMapper.map(this.categoryRepository.save(category));
	}
	
	
	
}












