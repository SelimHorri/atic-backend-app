package tn.cita.app.resource.v0;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
import tn.cita.app.constant.AppConstant;
import tn.cita.app.dto.request.RegisterRequest;
import tn.cita.app.dto.response.RegisterResponse;
import tn.cita.app.dto.response.api.ApiPayloadResponse;
import tn.cita.app.service.RegistrationService;

@RestController
@RequestMapping(AppConstant.API_CONTEXT_V0 + "/register")
@Slf4j
@RequiredArgsConstructor
public class RegistrationResource {
	
	private final RegistrationService registrationService;
	
	@PostMapping
	public ResponseEntity<ApiPayloadResponse<RegisterResponse>> register(
			@RequestBody 
			@NotNull(message = "Input should not be null") 
			@Valid final RegisterRequest registerRequest) {
		log.info("**RegistrationResource controller; RegisterResponse; register user...*\n");
		final var apiPayloadResponse = new ApiPayloadResponse<>(1, HttpStatus.OK, true, 
				this.registrationService.register(registerRequest));
		return ResponseEntity.ok(apiPayloadResponse);
	}
	
	@GetMapping("/{token}")
	public ResponseEntity<ApiPayloadResponse<String>> validateToken(
			@PathVariable("token") 
			@NotNull(message = "Input token should not be null") 
			final String token) {
		log.info("**RegistrationResource controller; String; register user...*\n");
		final var apiPayloadResponse = new ApiPayloadResponse<>(1, HttpStatus.OK, true, 
				this.registrationService.validateToken(token));
		return ResponseEntity.ok(apiPayloadResponse);
	}
	
	
	
}













