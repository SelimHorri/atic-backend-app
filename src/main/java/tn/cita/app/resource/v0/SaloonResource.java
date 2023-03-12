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
import tn.cita.app.model.dto.SaloonDto;
import tn.cita.app.model.dto.request.ClientPageRequest;
import tn.cita.app.model.dto.response.api.ApiResponse;
import tn.cita.app.service.v0.SaloonService;

@RestController
@RequestMapping(AppConstants.API_CONTEXT_V0 + "/saloons")
@Slf4j
@RequiredArgsConstructor
public class SaloonResource {
	
	private final SaloonService saloonService;
	
	@GetMapping
	public ResponseEntity<ApiResponse<Page<SaloonDto>>> findAll(@RequestParam final Map<String, String> params) {
		log.info("** Find all saloons.. *\n");
		final var saloons = this.saloonService.findAll(ClientPageRequest.from(params));
		return ResponseEntity.ok(new ApiResponse<>(saloons.toList().size(), HttpStatus.OK, true, saloons));
	}
	
	@GetMapping("/locations/state/{state}")
	public ResponseEntity<ApiResponse<Page<SaloonDto>>> findAllByLocationState(@PathVariable final String state, 
			@RequestParam final Map<String, String> params) {
		log.info("** Find all saloons by location state.. *\n");
		final var saloons = this.saloonService.findAllByLocationState(state, ClientPageRequest.from(params));
		return ResponseEntity.ok(new ApiResponse<>(saloons.toList().size(), HttpStatus.OK, true, saloons));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<SaloonDto>> findById(@PathVariable final String id) {
		log.info("** Find by id.. *\n");
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.saloonService.findById(Integer.parseInt(id))));
	}
	
	@GetMapping("/identifier/{identifier}")
	public ResponseEntity<ApiResponse<SaloonDto>> findByIdentifier(@PathVariable final String identifier) {
		log.info("** Find by identifier.. *\n");
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.saloonService.findByIdentifier(identifier.strip())));
	}
	
	@GetMapping("/code/{code}")
	public ResponseEntity<ApiResponse<Page<SaloonDto>>> findAllByCode(@PathVariable final String code) {
		log.info("** Find all salonns by code.. *\n");
		final var saloons = this.saloonService.findAllByCode(code);
		return ResponseEntity.ok(new ApiResponse<>(saloons.toList().size(), HttpStatus.OK, true, saloons));
	}
	
}







