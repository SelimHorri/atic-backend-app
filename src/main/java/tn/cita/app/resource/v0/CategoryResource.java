package tn.cita.app.resource.v0;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tn.cita.app.constant.AppConstant;
import tn.cita.app.dto.CategoryDto;
import tn.cita.app.dto.response.api.ApiResponse;
import tn.cita.app.service.v0.CategoryService;

@RestController
@RequestMapping(AppConstant.API_CONTEXT_V0 + "/categories")
@RequiredArgsConstructor
public class CategoryResource {
	
	private final CategoryService categoryService;
	
	@GetMapping("/{saloonId}")
	public ResponseEntity<ApiResponse<Page<CategoryDto>>> findAllBySaloonId(@PathVariable final String saloonId) {
		return ResponseEntity.ok(new ApiResponse<>(0, HttpStatus.OK, true, 
				new PageImpl<>(this.categoryService.findAllBySaloonId(Integer.parseInt(saloonId)))));
	}
	
	
	
}














