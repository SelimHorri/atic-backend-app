package tn.cita.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tn.cita.app.dto.CategoryDto;
import tn.cita.app.mapper.CategoryMapper;
import tn.cita.app.repository.CategoryRepository;
import tn.cita.app.service.CategoryService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
	
	private final CategoryRepository categoryRepository;
	
	@Override
	public List<CategoryDto> findAllBySaloonId(final Integer saloonId) {
		return this.categoryRepository.findAllBySaloonId(saloonId)
				.stream()
					.map(CategoryMapper::map)
					.distinct()
					.collect(Collectors.toUnmodifiableList());
	}
	
	
	
}












