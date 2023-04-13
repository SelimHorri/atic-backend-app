package tn.cita.app.domain.category;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.domain.category.employee.manager.model.CategoryRequest;
import tn.cita.app.exception.wrapper.CategoryNotFoundException;
import tn.cita.app.exception.wrapper.SaloonNotFoundException;
import tn.cita.app.mapper.CategoryMapper;
import tn.cita.app.model.domain.entity.Category;
import tn.cita.app.model.dto.CategoryDto;
import tn.cita.app.repository.CategoryRepository;
import tn.cita.app.repository.SaloonRepository;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
	
	private final CategoryRepository categoryRepository;
	private final SaloonRepository saloonRepository;
	
	@Override
	public List<CategoryDto> findAll() {
		log.info("** Find all categories.. *\n");
		return this.categoryRepository.findAll().stream()
				.map(CategoryMapper::map)
				.distinct()
				.toList();
	}
	
	@Override
	public CategoryDto findById(final Integer id) {
		log.info("** Find category by id.. *\n");
		return this.categoryRepository.findById(id)
				.map(CategoryMapper::map)
				.orElseThrow(() -> new CategoryNotFoundException("Category not found"));
	}
	
	@Override
	public CategoryDto findByIdentifier(final String identifier) {
		return this.categoryRepository.findByIdentifier(identifier.strip())
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
				.toList();
	}
	
	@Transactional
	@Override
	public CategoryDto save(final CategoryRequest categoryRequest) {
		
		log.info("** Save a category.. *\n");
		
		final var parentCategory = Optional.ofNullable(categoryRequest.parentCategoryId()).isPresent() ?
				this.categoryRepository.findById(categoryRequest.parentCategoryId()).orElseGet(Category::new) : null;
		
		final var saloon = this.saloonRepository.findById(categoryRequest.saloonId())
				.orElseThrow(() -> new SaloonNotFoundException("Saloon not found"));
		
		final var category = Category.builder()
				.name(categoryRequest.name().strip().toLowerCase())
				.parentCategory(parentCategory)
				.saloon(saloon)
				.build();
		
		return CategoryMapper.map(this.categoryRepository.save(category));
	}
	
	@Transactional
	@Override
	public CategoryDto update(final CategoryRequest categoryRequest) {
		
		log.info("** Update a category.. *\n");
		
		final var parentCategory = Optional.ofNullable(categoryRequest.parentCategoryId()).isPresent() ?
				this.categoryRepository.findById(categoryRequest.parentCategoryId()).orElseGet(Category::new) : null;
		
		final var saloon = this.saloonRepository.findById(categoryRequest.saloonId())
				.orElseThrow(() -> new SaloonNotFoundException("Saloon not found"));
		
		final var category = this.categoryRepository.findById(categoryRequest.categoryId())
				.orElseThrow(CategoryNotFoundException::new);
		category.setId(categoryRequest.categoryId());
		category.setName(categoryRequest.name().strip().toLowerCase());
		category.setParentCategory(parentCategory);
		category.setSaloon(saloon);
		
		return CategoryMapper.map(this.categoryRepository.save(category));
	}
	
}








