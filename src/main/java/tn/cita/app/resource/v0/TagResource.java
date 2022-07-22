package tn.cita.app.resource.v0;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.constant.AppConstants;
import tn.cita.app.dto.TagDto;
import tn.cita.app.dto.request.ClientPageRequest;
import tn.cita.app.dto.response.api.ApiResponse;
import tn.cita.app.service.v0.TagService;

@RestController
@RequestMapping(AppConstants.API_CONTEXT_V0 + "/tags")
@Slf4j
@RequiredArgsConstructor
public class TagResource {
	
	private final TagService tagService;
	
	@GetMapping
	public ResponseEntity<ApiResponse<Page<TagDto>>> findAll(@RequestParam final Map<String, String> params) {
		log.info("** TagResource; List TagDto; find all based on pageOffset.. *\n");
		final var tags = this.tagService.findAll(new ClientPageRequest(params));
		return ResponseEntity.ok(new ApiResponse<>(tags.toList().size(), HttpStatus.OK, true, tags));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<TagDto>> findById(@PathVariable final String id) {
		log.info("** TagResource; TagDto; find by id.. *\n");
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, this.tagService.findById(Integer.parseInt(id))));
	}
	
	
	
}












