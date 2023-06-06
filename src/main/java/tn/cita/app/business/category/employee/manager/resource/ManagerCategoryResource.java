package tn.cita.app.business.category.employee.manager.resource;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import tn.cita.app.business.category.employee.manager.model.CategoryRequest;
import tn.cita.app.business.category.employee.manager.service.ManagerCategoryService;
import tn.cita.app.model.dto.CategoryDto;
import tn.cita.app.model.dto.response.api.ApiResponse;
import tn.cita.app.util.UserRequestExtractorUtil;

@RestController
@RequestMapping("${app.api-version}" + "/employees/managers/categories")
@Slf4j
@RequiredArgsConstructor
public class ManagerCategoryResource {
	
	@Qualifier("managerRequestExtractorUtil")
	private final UserRequestExtractorUtil userRequestExtractorUtil;
	private final ManagerCategoryService managerCategoryService;
	
	@GetMapping
	public ResponseEntity<ApiResponse<Page<CategoryDto>>> fetchAll(final WebRequest webRequest) {
		log.info("** Fetch all manager categories.. *\n");
		return ResponseEntity.ok(new ApiResponse<>(0, HttpStatus.OK, true, 
				this.managerCategoryService.fetchAll(this.userRequestExtractorUtil.extractUsername(webRequest))));
	}
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<ApiResponse<CategoryDto>> fetchById(final WebRequest webRequest, 
			@PathVariable final String categoryId) {
		log.info("** Fetch manager category by id.. *\n");
		this.userRequestExtractorUtil.extractUsername(webRequest);
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.managerCategoryService.fetchById(Integer.parseInt(categoryId))));
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse<CategoryDto>> saveCategory(final WebRequest webRequest, 
			@RequestBody @Valid final CategoryRequest categoryRequest) {
		log.info("** Save manager category.. *\n");
		this.userRequestExtractorUtil.extractUsername(webRequest);
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, this.managerCategoryService
				.saveCategory(categoryRequest)));
	}
	
	@PutMapping
	public ResponseEntity<ApiResponse<CategoryDto>> updateCategory(final WebRequest webRequest, 
			@RequestBody @Valid final CategoryRequest categoryRequest) {
		log.info("** Update manager category.. *\n");
		this.userRequestExtractorUtil.extractUsername(webRequest);
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, this.managerCategoryService
				.updateCategory(categoryRequest)));
	}
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse<Boolean>> deleteCategory(final WebRequest webRequest, 
			@PathVariable final String categoryId) {
		log.info("** Delete manager category.. *\n");
		this.userRequestExtractorUtil.extractUsername(webRequest);
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.managerCategoryService.deleteCategory(Integer.parseInt(categoryId))));
	}
	
}




