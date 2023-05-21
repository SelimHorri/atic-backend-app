package tn.cita.app.business.reservation.customer.resource;

import java.util.Map;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.constant.AppConstants;
import tn.cita.app.business.reservation.customer.model.CustomerReservationResponse;
import tn.cita.app.business.reservation.customer.service.CustomerReservationService;
import tn.cita.app.model.dto.ReservationDto;
import tn.cita.app.model.dto.request.ClientPageRequest;
import tn.cita.app.model.dto.request.ReservationRequest;
import tn.cita.app.model.dto.response.api.ApiResponse;
import tn.cita.app.util.UserRequestExtractorUtil;

@RestController
@RequestMapping(AppConstants.API_CONTEXT_V0 + "/customers/reservations")
@Slf4j
@RequiredArgsConstructor
public class CustomerReservationResource {
	
	@Qualifier("customerRequestExtractorUtil")
	private final UserRequestExtractorUtil userRequestExtractorUtil;
	private final CustomerReservationService customerReservationService;
	
	@GetMapping
	public ResponseEntity<ApiResponse<CustomerReservationResponse>> fetchAllReservations(final WebRequest request,
			@RequestParam final Map<String, String> params) {
		log.info("** Fetch all customer reservations.. *\n");
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.customerReservationService.fetchAllReservations(this.userRequestExtractorUtil.extractUsername(request), 
						ClientPageRequest.from(params))));
	}
	
	@PutMapping("/cancel/{reservationId}")
	public ResponseEntity<ApiResponse<ReservationDto>> cancelReservation(final WebRequest request, 
			@PathVariable final String reservationId) {
		log.info("** Cancel customer reservation.. *\n");
		this.userRequestExtractorUtil.extractUsername(request);
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.customerReservationService.cancelReservation(Integer.parseInt(reservationId))));
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse<ReservationDto>> createReservation(final WebRequest request,
			@RequestBody @NotNull @Valid final ReservationRequest reservationRequest) {
		log.info("** Create customer reservation.. *\n");
		this.userRequestExtractorUtil.extractUsername(request);
		final var reservationDto = this.customerReservationService.createReservation(reservationRequest);
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, reservationDto));
	}
	
	@GetMapping("/search/{key}")
	public ResponseEntity<ApiResponse<CustomerReservationResponse>> searchAllBySaloonIdLikeKey(final WebRequest webRequest, 
			@PathVariable final String key) {
		log.info("** Search all customer reservations by saloonId like key.. *\n");
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.customerReservationService
					.searchAllByCustomerIdLikeKey(this.userRequestExtractorUtil.extractUsername(webRequest), key)));
	}
	
}







