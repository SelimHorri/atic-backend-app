package tn.cita.app.resource.v0;

import java.util.List;
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
import tn.cita.app.model.dto.LocationDto;
import tn.cita.app.model.dto.request.ClientPageRequest;
import tn.cita.app.model.dto.response.api.ApiResponse;
import tn.cita.app.service.v0.LocationService;

@RestController
@RequestMapping(AppConstants.API_CONTEXT_V0 + "/locations")
@Slf4j
@RequiredArgsConstructor
public class LocationResource {
	
	private final LocationService locationService;
	
	@GetMapping
	public ResponseEntity<ApiResponse<Page<LocationDto>>> findAll(@RequestParam final Map<String, String> params) {
		log.info("** Find all paged locations.. *\n");
		final var locations = this.locationService.findAll(ClientPageRequest.of(params));
		return ResponseEntity.ok(new ApiResponse<>(locations.toList().size(), HttpStatus.OK, true, locations));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<LocationDto>> findById(@PathVariable final String id) {
		log.info("** Find location by id.. *\n");
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.locationService.findById(Integer.parseInt(id))));
	}
	
	@GetMapping("/cities")
	public ResponseEntity<ApiResponse<List<String>>> fetchAllCities() {
		log.info("** Fetch all cities.. *\n");
		final var cities = this.locationService.fetchAllCities();
		return ResponseEntity.ok(new ApiResponse<>(cities.size(), HttpStatus.OK, true, cities));
	}
	
	@GetMapping("/states")
	public ResponseEntity<ApiResponse<List<String>>> fetchAllStates() {
		log.info("** Fetch all states.. *\n");
		final var states = this.locationService.fetchAllStates();
		return ResponseEntity.ok(new ApiResponse<>(states.size(), HttpStatus.OK, true, states));
	}
	
	
	
}















