package tn.cita.app.service.v0.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
		log.info("** Find all categories.. *\n");
		return this.categoryRepository.findAll().stream()
				.map(CategoryMapper::map)
				.distinct()
				.collect(Collectors.toUnmodifiableList());
	}
	
	@Override
	public CategoryDto findById(final Integer id) {
		log.info("** Find category by id.. *\n");
		return this.categoryRepository.findById(id)
				.map(CategoryMapper::map)
				.orElseThrow(() -> new CategoryNotFoundException("Category not found"));
	}
	
	@Override
	public List<CategoryDto> findAllBySaloonId(final Integer saloonId) {
		log.info("** Find all categories by saloonId.. *\n");
		return this.categoryRepository.findAllBySaloonId(saloonId).stream()
				.map(CategoryMapper::map)
				.distinct()
				.sorted(Comparator.comparing(CategoryDto::getName))
				.collect(Collectors.toUnmodifiableList());
	}
	
	@Transactional
	@Override
	public CategoryDto save(final CategoryRequest categoryRequest) {
		
		log.info("** Save a category.. *\n");
		
		final var parentCategory = Optional.ofNullable(categoryRequest.getParentCategoryId()).isPresent() ?
				this.categoryRepository.findById(categoryRequest.getParentCategoryId()).orElseGet(Category::new) : null;
		final var saloon = this.saloonService.getSaloonRepository()
				.findById(categoryRequest.getSaloonId())
					.orElseThrow(SaloonNotFoundException::new);
		
		final var category = Category.builder()
				.name(categoryRequest.getName().strip().toLowerCase())
				.parentCategory(parentCategory)
				.saloon(saloon)
				.build();
		
		return CategoryMapper.map(this.categoryRepository.save(category));
	}
	
	@Transactional
	@Override
	public CategoryDto update(final CategoryRequest categoryRequest) {
		
		log.info("** Update a category.. *\n");
		
		final var parentCategory = Optional.ofNullable(categoryRequest.getParentCategoryId()).isPresent() ?
				this.categoryRepository.findById(categoryRequest.getParentCategoryId()).orElseGet(Category::new) : null;
		final var saloon = this.saloonService.getSaloonRepository()
				.findById(categoryRequest.getSaloonId())
					.orElseThrow(SaloonNotFoundException::new);
		
		final var category = this.categoryRepository
				.findById(categoryRequest.getCategoryId())
					.orElseThrow(CategoryNotFoundException::new);
		category.setId(categoryRequest.getCategoryId());
		category.setName(categoryRequest.getName().strip().toLowerCase());
		category.setParentCategory(parentCategory);
		category.setSaloon(saloon);
		
		return CategoryMapper.map(this.categoryRepository.save(category));
	}
	
	
	
}












