package tn.cita.app.resource.v0;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tn.cita.app.constant.AppConstant;
import tn.cita.app.dto.response.CustomerContainerResponse;
import tn.cita.app.dto.response.api.ApiPayloadResponse;
import tn.cita.app.service.CustomerService;
import tn.cita.app.util.UserRequestExtractorUtil;

@RestController
@RequestMapping(AppConstant.API_CONTEXT_V0 + "/customers")
@RequiredArgsConstructor
public class CustomerResource {
	
	private final CustomerService customerService;
	private final UserRequestExtractorUtil requestExtractorUtil;
	
	@GetMapping("/profile")
	public ResponseEntity<ApiPayloadResponse<CustomerContainerResponse>> getProfile(
			final HttpServletRequest request) {
		return ResponseEntity.ok(new ApiPayloadResponse<>(1, HttpStatus.OK, true, 
				this.customerService.getProfileByUsername(this.requestExtractorUtil.extractUsername(request))));
	}
	
	@GetMapping("/favourites")
	public ResponseEntity<ApiPayloadResponse<CustomerContainerResponse>> getFavourites(
			final HttpServletRequest request) {
		return ResponseEntity.ok(new ApiPayloadResponse<>(1, HttpStatus.OK, true, 
				this.customerService.getFavouritesByUsername(this.requestExtractorUtil.extractUsername(request))));
	}
	
	
	
}













