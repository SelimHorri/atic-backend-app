package tn.cita.app.service.v0;

import java.util.List;

import tn.cita.app.dto.CategoryDto;
import tn.cita.app.dto.request.CategoryRequest;
import tn.cita.app.repository.CategoryRepository;

public interface CategoryService {
	
	CategoryRepository getCategoryRepository();
	List<CategoryDto> findAll();
	CategoryDto findById(final Integer id);
	List<CategoryDto> findAllBySaloonId(final Integer saloonId);
	CategoryDto save(final CategoryRequest categoryRequest);
	CategoryDto update(final CategoryRequest categoryRequest);
	
}










