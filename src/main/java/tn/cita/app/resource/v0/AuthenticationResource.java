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
import tn.cita.app.dto.request.LoginRequest;
import tn.cita.app.dto.response.ApiResponse;
import tn.cita.app.dto.response.LoginResponse;
import tn.cita.app.service.AuthenticationService;

@RestController
@RequestMapping(AppConstant.API_CONTEXT_V0 + "/authenticate")
@RequiredArgsConstructor
public class AuthenticationResource {
	
	private final AuthenticationService authenticationService;
	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse<LoginResponse>> login(
			@RequestBody 
			@NotNull(message = "Input login should not be null") 
			@Valid final LoginRequest loginRequest) {
		
		final var apiResponse = new ApiResponse<>(1, HttpStatus.OK, true, this.authenticationService.login(loginRequest));
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(apiResponse);
	}
	
	
	
}











