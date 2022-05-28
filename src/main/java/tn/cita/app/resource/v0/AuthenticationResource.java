package tn.cita.app.resource.v0;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.constant.AppConstant;
import tn.cita.app.dto.request.LoginRequest;
import tn.cita.app.dto.response.LoginResponse;
import tn.cita.app.dto.response.api.ApiPayloadResponse;
import tn.cita.app.service.v0.AuthenticationService;

@RestController
@RequestMapping(AppConstant.API_CONTEXT_V0 + "/authenticate")
@Slf4j
@RequiredArgsConstructor
public class AuthenticationResource {
	
	private final AuthenticationService authenticationService;
	
	@PostMapping
	public ResponseEntity<ApiPayloadResponse<LoginResponse>> authenticate(
			@RequestBody 
			@NotNull(message = "Input login should not be null") 
			@Valid final LoginRequest loginRequest) {
		
		log.info("**AuthenticationResource controller; authenticate user...*\n");
		final var apiPayloadResponse = new ApiPayloadResponse<>(1, HttpStatus.OK, true, 
				this.authenticationService.authenticate(loginRequest));
		return ResponseEntity.status(HttpStatus.OK)
				.body(apiPayloadResponse);
	}
	
	
	
}
















