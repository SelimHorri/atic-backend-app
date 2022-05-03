package tn.cita.app.resource.v0;

import javax.validation.constraints.NotBlank;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tn.cita.app.constant.AppConstant;
import tn.cita.app.dto.response.CustomerProfileResponse;
import tn.cita.app.dto.response.api.ApiPayloadResponse;
import tn.cita.app.service.CustomerService;

@RestController
@RequestMapping(AppConstant.API_CONTEXT_V0 + "/customers")
@RequiredArgsConstructor
public class CustomerResource {
	
	private final CustomerService customerService;
	
	@GetMapping("/profile/username/{username}")
	public ResponseEntity<ApiPayloadResponse<CustomerProfileResponse>> getCustomerProfileByUsername(
			@PathVariable("username")
			@NotBlank(message = "Input should not be blank") final String username) {
		
		return ResponseEntity.ok(new ApiPayloadResponse<>(1, HttpStatus.OK, true, 
				this.customerService.getCustomerProfileByUsername(username)));
	}
	
	
	
}













