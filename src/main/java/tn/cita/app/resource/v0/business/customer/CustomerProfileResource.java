package tn.cita.app.resource.v0.business.customer;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.constant.AppConstants;
import tn.cita.app.model.dto.CustomerDto;
import tn.cita.app.model.dto.request.ClientPageRequest;
import tn.cita.app.model.dto.request.CustomerProfileRequest;
import tn.cita.app.model.dto.response.CustomerProfileResponse;
import tn.cita.app.model.dto.response.api.ApiResponse;
import tn.cita.app.service.v0.business.customer.CustomerProfileService;
import tn.cita.app.util.UserRequestExtractorUtil;

@RestController
@RequestMapping(AppConstants.API_CONTEXT_V0 + "/customers/profile")
@Slf4j
@RequiredArgsConstructor
public class CustomerProfileResource {
	
	@Qualifier("customerRequestExtractorUtil")
	private final UserRequestExtractorUtil userRequestExtractorUtil;
	private final CustomerProfileService customerProfileService;
	
	@GetMapping
	public ResponseEntity<ApiResponse<CustomerProfileResponse>> fetchProfile(final WebRequest request, 
			@RequestParam final Map<String, String> params) {
		log.info("** Fetch customer profile info.. *\n");
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.customerProfileService.fetchProfile(this.userRequestExtractorUtil.extractUsername(request), 
						new ClientPageRequest(params))));
	}
	
	@PutMapping
	public ResponseEntity<ApiResponse<CustomerDto>> updateProfileInfo(final WebRequest webRequest, 
			@RequestBody @Valid final CustomerProfileRequest customerProfileRequest) {
		log.info("** Update customer profile info.. *\n");
		this.userRequestExtractorUtil.extractUsername(webRequest);
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.customerProfileService.updateProfileInfo(customerProfileRequest)));
	}
	
	
	
}













