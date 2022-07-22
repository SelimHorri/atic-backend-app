package tn.cita.app.resource.v0.business.employee.manager;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import lombok.RequiredArgsConstructor;
import tn.cita.app.constant.AppConstants;
import tn.cita.app.dto.CategoryDto;
import tn.cita.app.dto.request.CategoryRequest;
import tn.cita.app.dto.response.api.ApiResponse;
import tn.cita.app.service.v0.business.employee.manager.ManagerCategoryService;
import tn.cita.app.util.UserRequestExtractorUtil;

@RestController
@RequestMapping(AppConstants.API_CONTEXT_V0 + "/employees/managers/categories")
@RequiredArgsConstructor
public class ManagerCategoryResource {
	
	@Qualifier("managerRequestExtractorUtil")
	private final UserRequestExtractorUtil userRequestExtractorUtil;
	private final ManagerCategoryService managerCategoryService;
	
	@GetMapping
	public ResponseEntity<ApiResponse<Page<CategoryDto>>> getAll(final WebRequest webRequest) {
		return ResponseEntity.ok(new ApiResponse<>(0, HttpStatus.OK, true, 
				this.managerCategoryService.getAll(this.userRequestExtractorUtil.extractUsername(webRequest))));
	}
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<ApiResponse<CategoryDto>> getById(final WebRequest webRequest, 
			@PathVariable final String categoryId) {
		this.userRequestExtractorUtil.extractUsername(webRequest);
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.managerCategoryService.getById(Integer.parseInt(categoryId))));
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse<CategoryDto>> saveServiceDetail(final WebRequest webRequest, 
			@RequestBody @Valid final CategoryRequest categoryRequest) {
		this.userRequestExtractorUtil.extractUsername(webRequest);
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, this.managerCategoryService
				.saveCategory(categoryRequest)));
	}
	
	@PutMapping
	public ResponseEntity<ApiResponse<CategoryDto>> updateServiceDetail(final WebRequest webRequest, 
			@RequestBody @Valid final CategoryRequest categoryRequest) {
		this.userRequestExtractorUtil.extractUsername(webRequest);
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, this.managerCategoryService
				.updateCategory(categoryRequest)));
	}
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse<Boolean>> deleteCategory(final WebRequest webRequest, 
			@PathVariable final String categoryId) {
		this.userRequestExtractorUtil.extractUsername(webRequest);
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.managerCategoryService.deleteCategory(Integer.parseInt(categoryId))));
	}
	
	
	
}













