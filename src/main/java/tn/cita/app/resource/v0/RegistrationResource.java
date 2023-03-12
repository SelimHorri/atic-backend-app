package tn.cita.app.resource.v0;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.constant.AppConstants;
import tn.cita.app.model.dto.request.RegisterRequest;
import tn.cita.app.model.dto.response.RegisterResponse;
import tn.cita.app.model.dto.response.api.ApiResponse;
import tn.cita.app.service.v0.RegistrationService;

@RestController
@RequestMapping(AppConstants.API_CONTEXT_V0 + "/register")
@Slf4j
@RequiredArgsConstructor
public class RegistrationResource {
	
	private final RegistrationService registrationService;
	
	@PostMapping
	public ResponseEntity<ApiResponse<RegisterResponse>> register(@RequestBody @Valid final RegisterRequest registerRequest) {
		log.info("** Register user.. *\n");
		final var apiPayloadResponse = new ApiResponse<>(1, HttpStatus.OK, true, 
				this.registrationService.register(registerRequest));
		return ResponseEntity.ok(apiPayloadResponse);
	}
	
	@GetMapping("/{token}")
	public ResponseEntity<ApiResponse<String>> validateToken(@PathVariable final String token) {
		log.info("** Validate token for register user.. *\n");
		final var apiPayloadResponse = new ApiResponse<>(1, HttpStatus.OK, true, 
				this.registrationService.validateToken(token));
		return ResponseEntity.ok(apiPayloadResponse);
	}
	
}








