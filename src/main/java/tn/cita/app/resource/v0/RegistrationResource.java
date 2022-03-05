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
import tn.cita.app.constant.AppConstant;
import tn.cita.app.dto.request.RegisterRequest;
import tn.cita.app.dto.response.RegisterResponse;
import tn.cita.app.dto.response.api.ApiPayloadResponse;
import tn.cita.app.service.RegistrationService;

@RestController
@RequestMapping(AppConstant.API_CONTEXT_V0 + "/registration")
@RequiredArgsConstructor
public class RegistrationResource {
	
	private final RegistrationService registrationService;
	
	@PostMapping("/customer/register")
	public ResponseEntity<ApiPayloadResponse<RegisterResponse>> registerCustomer(
			@RequestBody 
			@NotNull(message = "Input should not be null") 
			@Valid final RegisterRequest registerRequest) {
		final var apiPayloadResponse = new ApiPayloadResponse<>(1, HttpStatus.OK, true, 
				this.registrationService.registerCustomer(registerRequest));
		return ResponseEntity.ok(apiPayloadResponse);
	}
	
	@PostMapping("/employee/register")
	public ResponseEntity<ApiPayloadResponse<RegisterResponse>> registerEmployee(
			@RequestBody 
			@NotNull(message = "Input should not be null") 
			@Valid final RegisterRequest registerRequest) {
		final var apiPayloadResponse = new ApiPayloadResponse<>(1, HttpStatus.OK, true, 
				this.registrationService.registerEmployee(registerRequest));
		return ResponseEntity.ok(apiPayloadResponse);
	}
	
	
	
}













