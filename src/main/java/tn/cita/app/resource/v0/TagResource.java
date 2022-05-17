package tn.cita.app.resource.v0;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.constant.AppConstant;
import tn.cita.app.dto.TagDto;
import tn.cita.app.dto.response.api.ApiPayloadResponse;
import tn.cita.app.service.TagService;

@RestController
@RequestMapping(AppConstant.API_CONTEXT_V0 + "/tags")
@Slf4j
@RequiredArgsConstructor
public class TagResource {
	
	private final TagService tagService;
	
	@GetMapping("/offset/{offset}")
	public ResponseEntity<ApiPayloadResponse<List<TagDto>>> findAll(@PathVariable @NotNull final String offset) {
		log.info("** TagResource; List TagDto; find all based on pageOffset.. *\n");
		final var tags = this.tagService.findAll(Integer.parseInt(offset));
		return ResponseEntity.ok(new ApiPayloadResponse<>(tags.size(), HttpStatus.OK, true, tags));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiPayloadResponse<TagDto>> findById(@PathVariable final String id) {
		log.info("** TagResource; TagDto; find by id.. *\n");
		return ResponseEntity.ok(new ApiPayloadResponse<>(1, HttpStatus.OK, true, this.tagService.findById(Integer.parseInt(id))));
	}
	
	
	
}












