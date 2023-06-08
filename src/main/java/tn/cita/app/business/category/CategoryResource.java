package tn.cita.app.business.category;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.cita.app.model.dto.CategoryDto;
import tn.cita.app.model.dto.response.api.ApiResponse;

@RestController
@RequestMapping("${app.api-version}" + "/categories")
@Slf4j
@RequiredArgsConstructor
public class CategoryResource {
	
	private final CategoryService categoryService;
	
	@GetMapping("/identifier/{identifier}")
	public ResponseEntity<ApiResponse<CategoryDto>> findByIdentifier(@PathVariable final String identifier) {
		log.info("** Find by identifier.. *\n");
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.categoryService.findByIdentifier(identifier.strip())));
	}
	
	@GetMapping("/{saloonId}")
	public ResponseEntity<ApiResponse<Page<CategoryDto>>> findAllBySaloonId(@PathVariable final String saloonId) {
		log.info("** Find all categories by saloonId.. *\n");
		return ResponseEntity.ok(new ApiResponse<>(0, HttpStatus.OK, true, 
				new PageImpl<>(this.categoryService.findAllBySaloonId(Integer.parseInt(saloonId)))));
	}
	
}




