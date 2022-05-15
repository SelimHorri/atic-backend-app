package tn.cita.app.resource.v0;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tn.cita.app.constant.AppConstant;
import tn.cita.app.dto.LocationDto;
import tn.cita.app.dto.response.api.ApiPayloadResponse;
import tn.cita.app.service.LocationService;

@RestController
@RequestMapping(AppConstant.API_CONTEXT_V0 + "/locations")
@RequiredArgsConstructor
public class LocationResource {
	
	private final LocationService locationService;
	
	@GetMapping
	public ResponseEntity<ApiPayloadResponse<List<LocationDto>>> findAll(@RequestParam(defaultValue = "1") final String offset) {
		final var locations = this.locationService.findAll(Integer.parseInt(offset));
		return ResponseEntity.ok(new ApiPayloadResponse<>(locations.size(), HttpStatus.OK, true, locations));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiPayloadResponse<LocationDto>> findById(@PathVariable final String id) {
		return ResponseEntity.ok(new ApiPayloadResponse<>(1, HttpStatus.OK, true, 
				this.locationService.findById(Integer.parseInt(id))));
	}
	
	
	
}















