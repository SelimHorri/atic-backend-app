package tn.cita.app.resource.v0.customer;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import lombok.RequiredArgsConstructor;
import tn.cita.app.constant.AppConstant;
import tn.cita.app.dto.response.CustomerRatingResponse;
import tn.cita.app.dto.response.api.ApiPayloadResponse;
import tn.cita.app.service.CustomerService;
import tn.cita.app.util.UserRequestExtractorUtil;

@RestController
@RequestMapping(AppConstant.API_CONTEXT_V0 + "/customers/ratings")
@RequiredArgsConstructor
public class CustomerRatingResource {
	
	@Qualifier("customerRequestExtractorUtil")
	private final UserRequestExtractorUtil userRequestExtractorUtil;
	private final CustomerService customerService;
	
	@GetMapping
	public ResponseEntity<ApiPayloadResponse<CustomerRatingResponse>> getRatings(
			final WebRequest request) {
		return ResponseEntity.ok(new ApiPayloadResponse<>(1, HttpStatus.OK, true, 
				this.customerService.getRatingsByUsername(this.userRequestExtractorUtil.extractUsername(request))));
	}
	
	
	
}















