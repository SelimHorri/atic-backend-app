package tn.cita.app.resource.v0;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tn.cita.app.constant.AppConstant;
import tn.cita.app.dto.request.ClientPageRequest;
import tn.cita.app.dto.response.CustomerContainerResponse;
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
	public ResponseEntity<ApiPayloadResponse<CustomerContainerResponse>> getProfile(
			final HttpServletRequest request) {
		return ResponseEntity.ok(new ApiPayloadResponse<>(1, HttpStatus.OK, true, 
				this.customerService.getProfileByUsername(this.userRequestExtractorUtil.extractUsername(request))));
	}
	
	@GetMapping("/favourites")
	public ResponseEntity<ApiPayloadResponse<CustomerContainerResponse>> getFavourites(final HttpServletRequest request, 
			@RequestParam(defaultValue = "1") final String offset, 
			@RequestParam(defaultValue = "" + AppConstant.PAGE_SIZE) final String size) {
		return ResponseEntity.ok(new ApiPayloadResponse<>(1, HttpStatus.OK, true, 
				this.customerService.getFavouritesByUsername(this.userRequestExtractorUtil.extractUsername(request), 
						new ClientPageRequest(Integer.parseInt(offset), Integer.parseInt(size)))));
	}
	
	@DeleteMapping("/favourites/{saloonId}")
	public ResponseEntity<ApiPayloadResponse<Boolean>> deleteFavourite(final HttpServletRequest request, 
			@PathVariable final String saloonId) {
		final Boolean isDeleted = this.customerService.deleteFavourite(this.userRequestExtractorUtil.extractUsername(request), 
				Integer.parseInt(saloonId));
		return ResponseEntity.ok(new ApiPayloadResponse<>(1, HttpStatus.OK, true, isDeleted));
	}
	
	@GetMapping("/ratings")
	public ResponseEntity<ApiPayloadResponse<CustomerContainerResponse>> getRatings(
			final HttpServletRequest request) {
		return ResponseEntity.ok(new ApiPayloadResponse<>(1, HttpStatus.OK, true, 
				this.customerService.getRatingsByUsername(this.userRequestExtractorUtil.extractUsername(request))));
	}
	
	@GetMapping("/reservations")
	public ResponseEntity<ApiPayloadResponse<CustomerContainerResponse>> getReservations(
			final HttpServletRequest request) {
		return ResponseEntity.ok(new ApiPayloadResponse<>(1, HttpStatus.OK, true, 
				this.customerService.getReservationsByUsername(this.userRequestExtractorUtil.extractUsername(request))));
	}
	
	
	
}













