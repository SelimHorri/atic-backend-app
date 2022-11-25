package tn.cita.app.resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.constant.AppConstants;
import tn.cita.app.exception.wrapper.ActuatorHealthException;
import tn.cita.app.model.dto.response.actuator.HealthActuatorResponse;
import tn.cita.app.model.dto.response.api.ApiResponse;

@RestController
@RequestMapping(AppConstants.API_CONTEXT_V0 + "/actuator")
@Slf4j
@RequiredArgsConstructor
public class ActuatorLiveness {
	
	@Value("${management.endpoints.web.base-path:actuator}")
	private String actuatorEndpoint;
	private final RestTemplate restTemplate;
	
	@GetMapping("/health")
	public ResponseEntity<ApiResponse<HealthActuatorResponse>> health() {
		
		log.info("** App health.. *\n");
		
		HealthActuatorResponse health = null;
		try {
			health = this.restTemplate.getForObject(String
					.format("%s/%s/health", ServletUriComponentsBuilder.fromCurrentContextPath().toUriString(), 
							actuatorEndpoint), HealthActuatorResponse.class);
		}
		catch (RestClientException e) {
			System.err.println(e.getMessage());
		}
		finally {
			if (health == null || !health.status().equalsIgnoreCase("UP"))
				throw new ActuatorHealthException("We're running into an issue 😬 \n"
						+ "Will be FIXED very soon, stay tunned..🤗");
		}
		
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, health));
	}
	
	
	
}










