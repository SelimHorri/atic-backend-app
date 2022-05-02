package tn.cita.app.resource.v0;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tn.cita.app.constant.AppConstant;
import tn.cita.app.dto.CredentialDto;
import tn.cita.app.dto.response.api.ApiPayloadResponse;
import tn.cita.app.service.CredentialService;

@RestController
@RequestMapping(AppConstant.API_CONTEXT_V0 + "/credentials")
@RequiredArgsConstructor
public class CredentialResource {
	
	private final CredentialService credentialService;
	
	@GetMapping("/username/{username}")
	public ResponseEntity<ApiPayloadResponse<CredentialDto>> findByUsername(
			@PathVariable("username") final String username) {
		
		final var payload = new ApiPayloadResponse<>(1, HttpStatus.OK, true, 
				this.credentialService.findByUsername(username));
		
		return ResponseEntity.ok(payload);
	}
	
	
	
}











