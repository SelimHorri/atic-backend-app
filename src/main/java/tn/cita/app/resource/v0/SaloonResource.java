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
import tn.cita.app.constant.AppConstant;
import tn.cita.app.dto.SaloonDto;
import tn.cita.app.dto.request.ClientPageRequest;
import tn.cita.app.dto.response.api.ApiPayloadResponse;
import tn.cita.app.service.SaloonService;

@RestController
@RequestMapping(AppConstant.API_CONTEXT_V0 + "/saloons")
@RequiredArgsConstructor
public class SaloonResource {
	
	private final SaloonService saloonService;
	
	@GetMapping
	public ResponseEntity<ApiPayloadResponse<Page<SaloonDto>>> findAll(@RequestParam final Map<String, String> params) {
		final var saloons = this.saloonService.findAll(new ClientPageRequest(params));
		return ResponseEntity.ok(new ApiPayloadResponse<>(saloons.toList().size(), HttpStatus.OK, true, saloons));
	}
	
	@GetMapping("/locations/state/{state}")
	public ResponseEntity<ApiPayloadResponse<Page<SaloonDto>>> findAllByLocationState(@PathVariable final String state, 
			@RequestParam final Map<String, String> params) {
		final var saloons = this.saloonService.findAllByLocationState(state, new ClientPageRequest(params));
		return ResponseEntity.ok(new ApiPayloadResponse<>(saloons.toList().size(), HttpStatus.OK, true, saloons));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiPayloadResponse<SaloonDto>> findById(@PathVariable final String id) {
		return ResponseEntity.ok(new ApiPayloadResponse<>(1, HttpStatus.OK, true, 
				this.saloonService.findById(Integer.parseInt(id))));
	}
	
	@GetMapping("/code/{code}")
	public ResponseEntity<ApiPayloadResponse<Page<SaloonDto>>> findAllByCode(@PathVariable final String code) {
		final var saloons = this.saloonService.findAllByCode(code);
		return ResponseEntity.ok(new ApiPayloadResponse<>(saloons.toList().size(), HttpStatus.OK, true, saloons));
	}
	
	
	
}














