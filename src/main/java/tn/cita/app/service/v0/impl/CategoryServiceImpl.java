package tn.cita.app.service.v0.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tn.cita.app.dto.CategoryDto;
import tn.cita.app.exception.wrapper.CategoryNotFoundException;
import tn.cita.app.mapper.CategoryMapper;
import tn.cita.app.repository.CategoryRepository;
import tn.cita.app.service.v0.CategoryService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
	
	private final CategoryRepository categoryRepository;
	
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
		return this.categoryRepository.findAllBySaloonId(saloonId)
				.stream()
					.map(CategoryMapper::map)
					.distinct()
					.collect(Collectors.toUnmodifiableList());
	}
	
	
	
}












