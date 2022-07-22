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
import tn.cita.app.constant.AppConstants;
import tn.cita.app.dto.LocationDto;
import tn.cita.app.dto.request.ClientPageRequest;
import tn.cita.app.dto.response.api.ApiResponse;
import tn.cita.app.service.v0.LocationService;

@RestController
@RequestMapping(AppConstants.API_CONTEXT_V0 + "/locations")
@RequiredArgsConstructor
public class LocationResource {
	
	private final LocationService locationService;
	
	@GetMapping
	public ResponseEntity<ApiResponse<Page<LocationDto>>> findAll(@RequestParam final Map<String, String> params) {
		final var locations = this.locationService.findAll(new ClientPageRequest(params));
		return ResponseEntity.ok(new ApiResponse<>(locations.toList().size(), HttpStatus.OK, true, locations));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<LocationDto>> findById(@PathVariable final String id) {
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.locationService.findById(Integer.parseInt(id))));
	}
	
	@GetMapping("/cities")
	public ResponseEntity<ApiResponse<List<String>>> getAllCities() {
		final var cities = this.locationService.getAllCities();
		return ResponseEntity.ok(new ApiResponse<>(cities.size(), HttpStatus.OK, true, cities));
	}
	
	@GetMapping("/states")
	public ResponseEntity<ApiResponse<List<String>>> getAllStates() {
		final var states = this.locationService.getAllStates();
		return ResponseEntity.ok(new ApiResponse<>(states.size(), HttpStatus.OK, true, states));
	}
	
	
	
}















