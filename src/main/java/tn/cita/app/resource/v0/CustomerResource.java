package tn.cita.app.resource.v0;

import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import lombok.RequiredArgsConstructor;
import tn.cita.app.constant.AppConstant;
import tn.cita.app.dto.request.ClientPageRequest;
import tn.cita.app.dto.response.CustomerFavouriteResponse;
import tn.cita.app.dto.response.CustomerProfileResponse;
import tn.cita.app.dto.response.CustomerRatingResponse;
import tn.cita.app.dto.response.CustomerReservationResponse;
import tn.cita.app.dto.response.api.ApiPayloadResponse;
import tn.cita.app.service.CustomerService;
import tn.cita.app.util.UserRequestExtractorUtil;

@RestController
@RequestMapping(AppConstant.API_CONTEXT_V0 + "/customers")
@RequiredArgsConstructor
public class CustomerResource {
	
	@Qualifier("customerRequestExtractorUtil")
	private final UserRequestExtractorUtil userRequestExtractorUtil;
	private final CustomerService customerService;
	
	@GetMapping("/profile")
	public ResponseEntity<ApiPayloadResponse<CustomerProfileResponse>> getProfile(final WebRequest request, 
			@RequestParam final Map<String, String> params) {
		return ResponseEntity.ok(new ApiPayloadResponse<>(1, HttpStatus.OK, true, 
				this.customerService.getProfileByUsername(this.userRequestExtractorUtil.extractUsername(request), 
						new ClientPageRequest(params))));
	}
	
	@GetMapping("/favourites")
	public ResponseEntity<ApiPayloadResponse<CustomerFavouriteResponse>> getFavourites(final WebRequest request, 
			@RequestParam final Map<String, String> params) {
		return ResponseEntity.ok(new ApiPayloadResponse<>(1, HttpStatus.OK, true, 
				this.customerService.getFavouritesByUsername(this.userRequestExtractorUtil.extractUsername(request), 
						new ClientPageRequest(params))));
	}
	
	@DeleteMapping("/favourites/{saloonId}")
	public ResponseEntity<ApiPayloadResponse<Boolean>> deleteFavourite(final WebRequest request, 
			@PathVariable final String saloonId) {
		final Boolean isDeleted = this.customerService.deleteFavourite(this.userRequestExtractorUtil.extractUsername(request), 
				Integer.parseInt(saloonId));
		return ResponseEntity.ok(new ApiPayloadResponse<>(1, HttpStatus.OK, true, isDeleted));
	}
	
	@GetMapping("/ratings")
	public ResponseEntity<ApiPayloadResponse<CustomerRatingResponse>> getRatings(
			final WebRequest request) {
		return ResponseEntity.ok(new ApiPayloadResponse<>(1, HttpStatus.OK, true, 
				this.customerService.getRatingsByUsername(this.userRequestExtractorUtil.extractUsername(request))));
	}
	
	@GetMapping("/reservations")
	public ResponseEntity<ApiPayloadResponse<CustomerReservationResponse>> getReservations(final WebRequest request,
			@RequestParam final Map<String, String> params) {
		return ResponseEntity.ok(new ApiPayloadResponse<>(1, HttpStatus.OK, true, 
				this.customerService.getReservationsByUsername(this.userRequestExtractorUtil.extractUsername(request), 
						new ClientPageRequest(params))));
	}
	
	
	
}













